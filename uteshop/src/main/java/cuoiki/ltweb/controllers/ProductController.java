package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.models.CategoryModel;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.impl.CategoryDAOImpl;
import cuoiki.ltweb.impl.ICategoryServiceImpl;
import cuoiki.ltweb.impl.ICommentServiceImpl;
import cuoiki.ltweb.impl.IProductServiceImpl;
import cuoiki.ltweb.impl.IUserServiceImpl;
import cuoiki.ltweb.impl.ProductDAOImpl;
import cuoiki.ltweb.impl.WishlistDAOImpl;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/products","/view/product"})
public class ProductController extends HttpServlet{

	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("/views/User.jsp").forward(req, resp);
        String path = req.getServletPath();

        // Kiểm tra xem URL có chứa 'forgotPassword' hay không
        if (path.contains("user")) {
		String searchKey = req.getParameter("search");
		String catId = req.getParameter("category");
		
		HttpSession session  = req.getSession();
		ICategoryDAO categoryDao = new CategoryDAOImpl();
		
		String message = "";
		
		
		IWishlistDAO wdao = new WishlistDAOImpl();
		IProductService productService = new IProductServiceImpl();
		IProductDAO productDao = new ProductDAOImpl();
		
		
		
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
		
		int endPage = (prodList.size())/2;
    	if ( prodList.size() % 2 != 0) {
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
        	
        	ProductModel product = productService.getProductsByProductId(Integer.parseInt(req.getParameter("pid")));
        	
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
        	
        	//khi bấm detail trong danh sách list product gọi đến đây set attribute cho session
        	req.setAttribute("shop",shop);
        	req.setAttribute("category", category);
        	req.setAttribute("product", product);
        	req.setAttribute("commentList", commentList);
        	req.getRequestDispatcher("/views/viewDetailProduct.jsp").forward(req, resp);
        }
	}

}
