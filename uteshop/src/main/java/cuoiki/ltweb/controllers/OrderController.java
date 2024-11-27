package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



import cuoiki.ltweb.models.*;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.mail.MailMessenger;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/order/add"})
public class OrderController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
				super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IShippingCompanyService shippingcompany_service = new IShippingCompanyServiceImpl();
		ICartService cart_service = new ICartServiceImpl();
		IOrderDetailService orderdetail_service = new IOrderDetailServiceImpl();
		IOrderService order_service = new IOrderServiceImpl();
		IProductService product_service = new IProductServiceImpl();
		
		HttpSession session = req.getSession();
		
		String paymentmethod = req.getParameter("payementMode");
		String shippingcompany = (String)session.getAttribute("shippingcompany");
		System.out.println(shippingcompany);
		float totalmoney = (float)session.getAttribute("totalMoney");
	
		long shippingcompany_id = shippingcompany_service.getIdShippingCompanyByName(shippingcompany);
		System.out.println(shippingcompany_id);
		UserModel user = (UserModel) session.getAttribute("activeUser");
		
		String shippingaddress = user.getAddress(); 
		
	    System.out.println(user.getAddress()+"haha");
				
		long millis = System.currentTimeMillis();
		Date currentDate = new Date(millis);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
		timestamp.setNanos(0);
		long order_id = 0;
		String status = "Order Placed";
			try {
				OrderModel order = new OrderModel(user.getId(),shippingcompany_id,currentDate,"New",totalmoney,paymentmethod,"paid",shippingaddress,timestamp,timestamp);
			
			
				boolean isSucess = order_service.insertOrder(order);
				if(isSucess==true)
				System.out.println("thuyen em di");
				OrderModel order_added = order_service.getOrderByUserIdAndCreatedAt(user.getId(), timestamp);
				
     			/*List<OrderModel> orders = order_service.getOrders(user.getId());
     			for (OrderModel order_item : orders) {
     				System.out.println("thuyen em di123");
     				  order_item.getCreatedAt().setNanos(0);
     				  timestamp.setNanos(0);
     				 System.out.println(order_item.getCreatedAt());
     				 System.out.println(timestamp);
     				if(order_item.getCreatedAt().equals(timestamp)) {
     					
     					System.out.println("thuyen em di hello kitti");
     				}
     			}*/
				if(session.getAttribute("from")!=null) {
					ProductModel prod = (ProductModel)session.getAttribute("productBuyNow");
					int prodQty = 1;
					float price = product_service.getProductPriceAfterDiscount(prod.getDiscount(),prod.getPrice()); //tính toán sau khi giảm
					float totalMoney = prodQty * price;
					OrderDetailModel orderedProduct = new OrderDetailModel(order_added.getId(),prod.getId(),price,prodQty,totalMoney);
					orderdetail_service.insertOrderedProduct(orderedProduct);
					session.removeAttribute("productBuyNow");
					session.removeAttribute("from");
				}
				else {
				List<CartModel> listOfCart = cart_service.getCartListByUserId(user.getId());

				for (CartModel item : listOfCart) {

					ProductModel prod = product_service.getProductsByProductId(item.getProductId());
				
					int prodQty = item.getQuantity();
					float price = product_service.getProductPriceAfterDiscount(prod.getDiscount(),prod.getPrice()); //tính toán sau khi giảm
					float totalMoney = prodQty * price;
					OrderDetailModel orderedProduct = new OrderDetailModel(order_added.getId(),item.getProductId(),price,prodQty,totalMoney);
					orderdetail_service.insertOrderedProduct(orderedProduct);
				}
				}
				
				session.removeAttribute("totalMoney");
				session.removeAttribute("shippingcompany");
			
				session.removeAttribute("deliverycharge"); //remove luôn cái thuộc tính này đã được set bên checkout controller , phục vụ cho giao diện

				//removing all product from cart after successful order
				cart_service.removeAllProductInCartByUserId(user.getId());
				session.setAttribute("cartCount",0);

			} catch (Exception e) {
				e.printStackTrace();
			}
	    session.setAttribute("order", "success");
	    MailMessenger.successfullyOrderPlaced(user.getUsername(),user.getEmail(),Long.toString(order_id),currentDate.toString());
        resp.sendRedirect("/uteshop/views/index.jsp");
	}
	

}
