package cuoiki.ltweb.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.models.WishlistModel;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/v1/api/getUserInfo","/user/profile","/user/profile/update"})
@MultipartConfig
public class UserController extends HttpServlet{
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repositorydoanlaptrinhweb\\uteshop\\src\\main\\webapp\\Images";
	public static final String DEFAULT_FILENAME = "default.file";
	IUserService user_service = new IUserServiceImpl();
	IWishlistService wishlist_service = new IWishlistServiceImpl();
	IProductService product_service = new IProductServiceImpl();
	IOrderDetailService order_detail_service = new IOrderDetailServiceImpl();
	IOrderService order_service = new IOrderServiceImpl();
	IShopService shop_service = new IShopServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		//check coi có activeUser không -> không có thì chuyển hướng sang login
        HttpSession session = req.getSession();
        UserModel user1 = (UserModel)session.getAttribute("activeUser");
        if (path.contains("/v1/api/getUserInfo")) {
   		 String userIdStr = req.getParameter("user_id");
   		 System.out.println(userIdStr);
   		 
   		 long userid = Long.parseLong(userIdStr);

   	        // Giả sử bạn có phương thức để lấy thông tin người dùng từ cơ sở dữ liệu
   	        UserModel user = user_service.findById(userid);

   	        resp.setContentType("application/json");
   	        resp.setCharacterEncoding("UTF-8");

   	        PrintWriter out = resp.getWriter();
   	        JSONObject json = new JSONObject();

   	        if (user != null) {
   	            json.put("name", user.getFullname());
   	            json.put("image", user.getImage());
   	        } else {
   	            json.put("error", "User not found");
   	        }

   	        out.print(json.toString());
   	        out.flush();
        
        }
        if(path.contains("profile")) {
        
          if(user1 == null) {
        	  resp.sendRedirect("/views/login.jsp");
        	  return;
          }
          
          List<WishlistModel> list_wishlist = wishlist_service.getListByUserId(user1.getId());
          List<ProductModel> list_product = new ArrayList<ProductModel>();
          req.setAttribute("wlist", list_wishlist);
          for (WishlistModel wishlist : list_wishlist) {
        	  ProductModel product = product_service.getProductsByProductId(wishlist.getProductId());
        	  int price_after_discount = product_service.getProductPriceAfterDiscount(product.getDiscount(), product.getPrice());
        	
        	  product.setPrice_after_discount(price_after_discount);

          list_product.add(product);
          }
          List<OrderModel> order_list = order_service.getOrders(user1.getId());
          
          for(OrderModel order : order_list) {
          List<OrderDetailModel> order_detail_list = order_detail_service.getAllOrderedProduct(order.getId());
          for(OrderDetailModel order_detail : order_detail_list) {
          ProductModel prod = product_service.getProductsByProductId(order_detail.getProductId());
          int price_after_discount = product_service.getProductPriceAfterDiscount(prod.getDiscount(), prod.getPrice());
      	
    	  prod.setPrice_after_discount(price_after_discount);
          order_detail.setProduct(prod);
          }
          order.setOrder_detail_list(order_detail_list);
          }
          
          List<ShopModel> shop_list = shop_service.getShopsByVendorId(user1.getId());
          
          
          req.setAttribute("shopList",shop_list);
          req.setAttribute("orderList",order_list);
          req.setAttribute("prodsinwishlist", list_product);
          req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
          
        }
		
	    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
	     HttpSession session = req.getSession();
	     UserModel user = (UserModel)session.getAttribute("activeUser");
		if(path.contains("update")) {
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
							   
					        } 
						part.write(uploadPath + File.separator + fileName);
					}else
					{
						
						
					}

				}
				
				String fullname = req.getParameter("name");
				String email = req.getParameter("email");
				String phone_number = req.getParameter("mobile_no");
				String dateStr = req.getParameter("dateOfBirth");
				Date date = Date.valueOf(dateStr);
				String address = req.getParameter("address");
				
				if(user.getEmail().equals(email)!=true) {
				if(user_service.checkExistEmail(email)==true )
				{
					String message = "Email is registered by other user ";
					session.setAttribute("message1",message);
					resp.sendRedirect("/uteshop/user/profile");
					return;
				}
				}
				if(user.getPhoneNumber().equals(phone_number)!=true) {
					if(user_service.checkExistEmail(phone_number)==true )
					{
						String message = "Phone number is registered by other user ";
						session.setAttribute("message1",message);
						resp.sendRedirect("/uteshop/user/profile");
						return;
					}
					}
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
				
				UserModel user_updated = user_service.findById(user.getId());
				user_updated.setFullname(fullname);
				user_updated.setEmail(email);
				user_updated.setPhoneNumber(phone_number);
				user_updated.setDateOfBirth(date);
				user_updated.setAddress(address);
				user_updated.setImage(image);
				
				 user_service.update(user_updated);
				
				 String message = "Updated successfully";
				 session.setAttribute("message1",message);
			} catch (FileNotFoundException fne) {
				String message = "message1"+ "There was an error: " + fne.getMessage();
			    session.setAttribute("message1",message);
			}
			UserModel user_up = user_service.findById(user.getId());
			session.setAttribute("activeUser",user_up);
			resp.sendRedirect("/uteshop/user/profile");
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
