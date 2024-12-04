package cuoiki.ltweb.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShippingCompanyModel;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.models.CategoryModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.mail.MailMessenger;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/registerShop","/user/viewshop","/revenue","/user/updateShop","/user/orderdetail/update"})
@MultipartConfig
public class ShopController extends HttpServlet{
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repositorydoanlaptrinhweb\\uteshop\\src\\main\\webapp\\Images";
	public static final String DEFAULT_FILENAME = "default.file";
	IShopService shop_service = new IShopServiceImpl();
	IOrderService order_service = new IOrderServiceImpl();
	IOrderDetailService order_detail_service = new IOrderDetailServiceImpl();
	IProductService product_service = new IProductServiceImpl();
	IUserService user_service = new IUserServiceImpl();
	IShippingCompanyService shipping_company_service = new IShippingCompanyServiceImpl();
	ICategoryService category_service = new ICategoryServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("revenue")) {
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String shopIdParam = req.getParameter("shop_id");
        if (shopIdParam == null || shopIdParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing shop_id parameter\"}");
            return;
        }

        int shopId;
        try {
            shopId = Integer.parseInt(shopIdParam);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid shop_id parameter\"}");
            return;
        }

        try {
            List<Map<String, Object>> revenueData = shop_service.getDailyRevenue(shopId);
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(revenueData));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Internal server error\"}");
        }
    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("orderdetail/update")) {
			if(req.getParameter("from")!=null)
			{
				String odidStr = req.getParameter("odid");
				String status = req.getParameter("status");
				long odid = Long.valueOf(odidStr);
				order_detail_service.updateOrderDetail(odid, status);
				resp.sendRedirect("/uteshop/user/profile");
				return;
			}
			String odidStr = req.getParameter("odid");
			String status = req.getParameter("status");
			String shopidStr = req.getParameter("shopid");
			
		
			long odid = Long.valueOf(odidStr);
			
			order_detail_service.updateOrderDetail(odid, status);
			
			req.setAttribute("shopid", shopidStr);
			
			req.getRequestDispatcher("/user/viewshop").forward(req, resp);
			return;
		}
		if(path.contains("viewshop")) {
		
			String shopidStr = req.getParameter("shopid");
			long shopid = Long.valueOf(shopidStr);
			ShopModel shop = shop_service.findById(shopid);
			if(shop.getIs_active()==false)
			{
				String message = "Shop của bạn chưa được duyệt";
				HttpSession session = req.getSession();
				session.setAttribute("message", message);
				resp.sendRedirect("/uteshop/user/profile");				
				return;
			}
			List<OrderModel> order_list = order_service.getAllOrders();
			List<ProductModel> product_list = product_service.getAllProductsByShopId(shopid);
			List<CategoryModel> category_list = category_service.findAll();
			
			for(ProductModel product : product_list) {
			
				product.setCategory_name(category_service.getCategoryName(product.getCategory_id()));
			}
			
			for (OrderModel order : order_list) {
			List<OrderDetailModel> order_detail_list = order_detail_service.getAllOrderedProduct(order.getId());
			UserModel user = user_service.findById(order.getUserid());
			ShippingCompanyModel shippingunit = shipping_company_service.findById(order.getShippingcompanyid());
			order.setUser(user);
			Iterator<OrderDetailModel> iterator = order_detail_list.iterator();
			while (iterator.hasNext()) {
			    OrderDetailModel orderdetail = iterator.next();
			    ProductModel product = product_service.getProductsByProductId(orderdetail.getProductId());
			    orderdetail.setProduct(product);

			    boolean found = false;
			    for (ProductModel product1 : product_list) {
			        if (orderdetail.getProduct().getId() == product1.getId()) {
			            found = true;
			            break;
			        }
			    }
			    if (!found) {
			        iterator.remove(); // Xóa phần tử nếu không tìm thấy sản phẩm trong product_list
			    }
			}
			order.setShipunit(shippingunit);
			order.setOrder_detail_list(order_detail_list);
			
			}
			
			
			
			for(ProductModel product11 : product_list)
			{
				System.out.println("thaihung");
				System.out.println(product11.getId());
			}
			req.setAttribute("prodList", product_list);
			req.setAttribute("orderList", order_list);
			req.setAttribute("categoryList", category_list);
			req.setAttribute("shop",shop);
			req.getRequestDispatcher("/views/viewDetailShop.jsp").forward(req, resp);
			return;
		}
		if(path.contains("updateShop")) {
			String shopname = req.getParameter("name");
			String email = req.getParameter("email");
			String phonenumber = req.getParameter("mobile_no");
			String description = req.getParameter("description");
			String address = req.getParameter("address");
			String shopidStr = req.getParameter("shopid");
		
			
			HttpSession session = req.getSession();
			UserModel user = (UserModel)session.getAttribute("activeUser");
			System.out.print("Cuc cu 2222");
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
						part.write(uploadPath + File.separator + fileName);
					}else
					{
						
						
					}

				}
				}
				//UserModel user = (UserModel)session.getAttribute("activeUser");
		
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
				
				ShopModel shop = shop_service.findById(Long.valueOf(shopidStr));
				
				shop.setName(shopname);
				shop.setEmail(email);
				shop.setPhone_number(phonenumber);
				shop.setDescription(description);
				shop.setAddress(address);
				shop.setUpdated_at(timestamp);
				shop.setLogo(fileName);
				shop_service.update(shop);
				
				
				session.setAttribute("shopeditsuccess","Shop has been edited success");
			} catch (FileNotFoundException fne) {
				session.setAttribute("shopeditfail","There was an error during creating");
			}
		    MailMessenger.RegisteredShopOwnerWaitToAccepted(user.getUsername(),user.getEmail(),shopname);;
	        resp.sendRedirect("/uteshop/views/index.jsp");
	        return;
		}
		
		String shopname = req.getParameter("shop_name");
		String shopdescription = req.getParameter("description");
		String shopaddress = req.getParameter("address");
		String phonenumber = req.getParameter("phone_number");
		String shopemail = req.getParameter("email");
		
		HttpSession session = req.getSession();
		UserModel user = (UserModel)session.getAttribute("activeUser");
		System.out.print("Cuc cu 2222");
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
					part.write(uploadPath + File.separator + fileName);
				}else
				{
					
					
				}

			}
			}
			//UserModel user = (UserModel)session.getAttribute("activeUser");
	
			long millis = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
			timestamp.setNanos(0);
			
			ShopModel sm = new ShopModel(user.getId(),shopname,shopdescription,image,shopaddress,phonenumber,shopemail,false, timestamp, timestamp);
			boolean isSuccess = shop_service.insertShop(sm);
			
			session.setAttribute("shopregistersuccess","Shop has been register success, wait to be accepted");
		} catch (FileNotFoundException fne) {
			session.setAttribute("shopregisterfail","There was an error during creating");
		}
	    MailMessenger.RegisteredShopOwnerWaitToAccepted(user.getUsername(),user.getEmail(),shopname);;
        resp.sendRedirect("/uteshop/views/index.jsp");
	}
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return DEFAULT_FILENAME;
	}
	
	

}
