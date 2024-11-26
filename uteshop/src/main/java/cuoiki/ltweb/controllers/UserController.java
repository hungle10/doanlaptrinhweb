package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.models.WishlistModel;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/v1/api/getUserInfo","/user/profile"})
public class UserController extends HttpServlet{
	IUserService user_service = new IUserServiceImpl();
	IWishlistService wishlist_service = new IWishlistServiceImpl();
	IProductService product_service = new IProductServiceImpl();
	IOrderDetailService order_detail_service = new IOrderDetailServiceImpl();
	IOrderService order_service = new IOrderServiceImpl();

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
          order_detail.setProduct(prod);
          }
          order.setOrder_detail_list(order_detail_list);
          }
          
          req.setAttribute("orderList",order_list);
          req.setAttribute("prodsinwishlist", list_product);
          req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
          
        }
		
	    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
