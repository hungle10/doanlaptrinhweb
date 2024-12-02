package cuoiki.ltweb.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.models.CategoryModel;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.impl.CategoryDAOImpl;
import cuoiki.ltweb.impl.ICategoryServiceImpl;
import cuoiki.ltweb.impl.ICommentServiceImpl;
import cuoiki.ltweb.impl.IProductServiceImpl;
import cuoiki.ltweb.impl.IUserServiceImpl;
import cuoiki.ltweb.impl.ProductDAOImpl;
import cuoiki.ltweb.impl.ShopDAOImpl;
import cuoiki.ltweb.impl.WishlistDAOImpl;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/products","/view/product","/user/updateproduct","/user/deleteproduct","/user/addproduct"})
@MultipartConfig
public class ProductController extends HttpServlet{
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repositorydoanlaptrinhweb\\uteshop\\src\\main\\webapp\\Images";
	public static final String DEFAULT_FILENAME = "default.file";



	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("/views/User.jsp").forward(req, resp);
        String path = req.getServletPath();

        // Kiểm tra xem URL có chứa 'forgotPassword' hay không
        if (path.contains("user")) {
        	String shopId ="";
		String searchKey = req.getParameter("search");
		String catId = req.getParameter("category");
		if(req.getParameter("shopid")!=null) {
		 shopId = req.getParameter("shopid");
		}
		HttpSession session  = req.getSession();
		ICategoryDAO categoryDao = new CategoryDAOImpl();
		
		String message = "";
		
		
		IWishlistDAO wdao = new WishlistDAOImpl();
		IProductService productService = new IProductServiceImpl();
		IProductDAO productDao = new ProductDAOImpl();
		IShopDAO shopDao = new ShopDAOImpl();
		
		
		
		List<ProductModel> prodList = null;
		List<ProductModel> prodListFiltered = null;
		
		
		int currentPage = 1;
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
		//check xem người dùng có search không
		if (searchKey != null) {
			//nếu nó có gõ chữ gì đó để search
			if (!searchKey.isEmpty()) {
				message = "Showing results for \"" + searchKey + "\"";
			//nếu nó không gõ gì hết mà bấm search
			}else{
				message = "No product found!";
			}
			//
			prodList = productDao.getAllProductsBySearchKey(searchKey);
			// nó có gõ chữ và bấm search -> hàm này phân trang
			prodListFiltered = productDao.getAllProductsBySearchKey(searchKey,currentPage);

		} 
		//nếu nó có pick menu category để xổ ra các lựa chọn và nó chọn khác all product
		else if (catId != null && !(catId.trim().equals("0"))) { //nếu cateId khác 0
			
			prodList = productDao.getAllProductsByCategoryId(Integer.parseInt(catId.trim()));
			//phân trang
			prodListFiltered = productDao.getAllProductsByCategoryId(Integer.parseInt(catId.trim()),currentPage);
			message = "Showing results for \"" + categoryDao.getCategoryName(Integer.parseInt(catId.trim())) + "\"";
		}
		else if(shopId != "") {
			prodList = productDao.getAllProductsByShopId(Integer.parseInt(shopId.trim()));
			//phân trang
			prodListFiltered = productDao.getAllProductsByShopId(Integer.parseInt(shopId.trim()),currentPage);
			message = "Showing results for \"" + shopDao.findById(Integer.parseInt(shopId.trim())).getName() + "\"";
			ShopModel shopmodel = shopDao.findById(Long.valueOf(shopId.trim()));
			req.setAttribute("shopmodel",shopmodel);
		}
		// tk user này nhấn chữ product kế bên UTE
		else {
			
			prodList = productDao.getAllProducts();
			prodListFiltered = productDao.getProductsBasedOnPageNumber(currentPage);
			message = "All Products";
		}
//sau khi tìm kiếm nhưng không có hàng nào khớp thì show hết product
		if (prodList != null && prodList.size() == 0) {
			String itemName;

			// Kiểm tra xem searchKey có khác null không
			if (searchKey != null) {
			    itemName = searchKey;  // Nếu có, gán giá trị cho itemName
			} else {
			    // Nếu không, lấy tên danh mục từ categoryDao dựa trên catId
			    itemName = categoryDao.getCategoryName(Integer.parseInt(catId.trim()));
			}
			// Tạo thông báo với itemName
			message = "No items are available for \"" + itemName + "\"";
			prodList = productDao.getAllProducts();
			prodListFiltered = productDao.getProductsBasedOnPageNumber(currentPage);
		}
		
		
		for(int i=0;i<prodListFiltered.size();i++)
		{
			int price_after_discount = productService.getProductPriceAfterDiscount(prodListFiltered.get(i).getDiscount(),prodListFiltered.get(i).getPrice());
			prodListFiltered.get(i).setPrice_after_discount(price_after_discount);
		}
		
		List<ProductModel> prodListWish = new ArrayList<ProductModel>();
		List<ProductModel> prodListUnWish = new ArrayList<ProductModel>();
		UserModel user =(UserModel) session.getAttribute("activeUser");	
		if(user==null) {
			resp.sendRedirect("/uteshop/views/login.jsp");
			return;
		}
		for (int i = 0; i < prodListFiltered.size(); i++) {
			ProductModel product = prodListFiltered.get(i);
			if(wdao.getWishlist(user.getId(),product.getId())==true)
			{
				prodListWish.add(product);
			    System.out.println(product.getName());
			}
			else
			{
				prodListUnWish.add(product);
			}
		    
		}
		
		int endPage = (prodList.size())/20;
    	if ( prodList.size() % 20 != 0) {
    		endPage ++;
    	}
		
        req.setAttribute("prodListFiltered",prodListFiltered);
		req.setAttribute("prodListWish",prodListWish);
		req.setAttribute("prodListUnWish", prodListUnWish);
		req.setAttribute("endPage",endPage);
		req.setAttribute("currentpage",currentPage);
		req.setAttribute("message", message);
		req.getRequestDispatcher("/views/products.jsp").forward(req, resp);
	 }
        if (path.contains("view")) {
        	IProductService productService = new IProductServiceImpl();
        	ICategoryService category_service = new ICategoryServiceImpl();
        	ICommentService comment_service = new ICommentServiceImpl();	
        	IUserService user_service = new IUserServiceImpl();
        	
        	long prodid = Long.valueOf(req.getParameter("pid"));
        	
        	ProductModel product = productService.getProductsByProductId(prodid);
        	
        	System.out.println("hahaha"+product.getName());
        	
        	List<CommentModel> commentList = comment_service.getAllCommentsOfProduct(product.getId());
        	List<UserModel> userList =  user_service.findAll();
        	
        	for (CommentModel comment : commentList) {
        		for(UserModel user : userList ){
        			if(comment.getUser_id() == user.getId())
        			{
        				comment.setImage_user(user.getImage());
        				comment.setUsername(user.getUsername());
        				System.out.println("Son tung mtp");
        				System.out.println(comment.getImage_user());
        			}
        		}
        	}

        	
        	int price_after_discount = productService.getProductPriceAfterDiscount(product.getDiscount(),product.getPrice());
        	product.setPrice_after_discount(price_after_discount);
        	CategoryModel category = category_service.getCategoryById(product.getCategory_id());
        	
        	ShopModel shop = productService.getShopByProductId(product.getId());
        	
        	System.out.println(shop.getName());
        	//khi bấm detail trong danh sách list product gọi đến đây set attribute cho session
        	req.setAttribute("shop",shop);
        	req.setAttribute("category", category);
        	req.setAttribute("product", product);
        	req.setAttribute("commentList", commentList);
        	req.getRequestDispatcher("/views/viewDetailProduct.jsp").forward(req, resp);
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("/user/addproduct")) {
			String shopidStr = req.getParameter("shopid");
			
			long shopid = Long.valueOf(shopidStr);
			
			
			String nameProd = req.getParameter("name");
			String descriptionProd = req.getParameter("description");
			String priceProdStr = req.getParameter("price");
			float pricePrd = Float.valueOf(priceProdStr);
			String discountProdStr = req.getParameter("discount");
			int discount = Integer.valueOf(discountProdStr);
			String quantityProdStr = req.getParameter("quantity");
			int quantity = Integer.valueOf(quantityProdStr);
			String categoryId = req.getParameter("categoryType");
			
			IProductService product_service = new IProductServiceImpl();
			ICategoryService category_service = new ICategoryServiceImpl();
			
			CategoryModel cate = category_service.getCategoryById(Long.valueOf(categoryId));
		    
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try { 
				String fileName = "";
				String image = "";
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = (List<Part>) req.getParts();
				    
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if (fileName !=  "") {
						
						String partName = part.getName();
						   if ("fileimage".equals(partName)) {
							   image = fileName; // Lưu tên ảnh
							   
					        }
						part.write(uploadPath + File.separator + fileName);
					}

				}
		
			    
				
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
				
				ProductModel prod = new ProductModel(nameProd,pricePrd,descriptionProd,quantity,discount,image,cate.getId(),timestamp,timestamp,shopid);
				product_service.insert(prod);
				
				
				req.setAttribute("message", "Product has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			req.getRequestDispatcher("/user/viewshop?shopid=" + shopidStr).forward(req, resp);
			return;
		}
		if(path.contains("/user/updateproduct")) {
			
String shopidStr = req.getParameter("shopid");
			
			long shopid = Long.valueOf(shopidStr);
			
			String idProdStr = req.getParameter("id");
			long idProd = Long.valueOf(idProdStr);
			
			String nameProd = req.getParameter("name");
			String descriptionProd = req.getParameter("description");
			String priceProdStr = req.getParameter("price");
			float pricePrd = Float.valueOf(priceProdStr);
			String discountProdStr = req.getParameter("discount");
			int discount = Integer.valueOf(discountProdStr);
			String quantityProdStr = req.getParameter("quantity");
			int quantity = Integer.valueOf(quantityProdStr);
			String categoryId = req.getParameter("categoryType");
			
			IProductService product_service = new IProductServiceImpl();
			ICategoryService category_service = new ICategoryServiceImpl();
			
			CategoryModel cate = category_service.getCategoryById(Long.valueOf(categoryId));
		    
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try { 
				String fileName = "";
				String image = "";
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = (List<Part>) req.getParts();
				    
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if (fileName !=  "") {
						
						String partName = part.getName();
						   if ("fileimage".equals(partName)) {
							   image = fileName; // Lưu tên ảnh
							   
					        }
						part.write(uploadPath + File.separator + fileName);
					}

				}
		
			    
				
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
				
				ProductModel prod = product_service.getProductsByProductId(idProd);
				prod.setName(nameProd);
				prod.setPrice(pricePrd);
				prod.setDescription(descriptionProd);
				prod.setQuantity(quantity);
				prod.setDiscount(discount);
				prod.setImage(image);
				prod.setCategory_id(cate.getId());
				prod.setUpdatedAt(timestamp);
				prod.setShop_id(shopid);
				
				
				product_service.update(prod);
				
				
				req.setAttribute("message", "Product has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			req.getRequestDispatcher("/user/viewshop?shopid=" + shopidStr).forward(req, resp);
			return;
		}
		if(path.contains("/user/deleteproduct")) {
			
			String shopidStr = req.getParameter("shopid");
			String prodIdStr = req.getParameter("pid");
			long proId = Long.valueOf(prodIdStr);
			IProductService product_service = new IProductServiceImpl();
		    product_service.delete(proId);
		    req.getRequestDispatcher("/user/viewshop?shopid=" + shopidStr).forward(req, resp);
		    return;
		}
	}
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return DEFAULT_FILENAME;
	}
	

}
