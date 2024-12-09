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
import cuoiki.ltweb.paypal.PayPalService;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.mail.MailMessenger;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/order/add","/user/order/update"})
public class OrderController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
				super.doPost(req, resp);
	}
	private float convertVNDToUSD(float amountInVND) {
	    final float exchangeRate = 25376.05f; // 1 USD = 25,376.05 VND
	    return amountInVND / exchangeRate;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
	
		if(path.contains("update")) {
			
			String order_detail_idStr = req.getParameter("odid");
			long order_detail = Long.valueOf(order_detail_idStr);
			String status = req.getParameter("status");
			
			
			return;
		}
		IShippingCompanyService shippingcompany_service = new IShippingCompanyServiceImpl();
		ICartService cart_service = new ICartServiceImpl();
		IOrderDetailService orderdetail_service = new IOrderDetailServiceImpl();
		IOrderService order_service = new IOrderServiceImpl();
		IProductService product_service = new IProductServiceImpl();
		
		HttpSession session = req.getSession();
		
		String paymentmethod = req.getParameter("payementMode");
		
	
		float totalmoney = (float)session.getAttribute("totalMoney");
	
		long shippingcompany_id = (long)session.getAttribute("shippingcompanyId"); //
		
		
		System.out.println(shippingcompany_id);
		UserModel user = (UserModel) session.getAttribute("activeUser");
		
		String shippingaddress = user.getAddress(); 
		
	    System.out.println(user.getAddress()+"haha");
				
		long millis = System.currentTimeMillis();
		Date currentDate = new Date(millis);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
		timestamp.setNanos(0);
		long order_id = 0;
		String status_payment = "paid";
			try {
				String status = "";
				if(paymentmethod.equals("COD")) {
					status = "COD";
					status_payment = "unpaid";
				}
				else {
					status ="cardpayment";
				    String approveLink = PayPalService.createPayment(convertVNDToUSD(totalmoney), "USD", "http://localhost:8082/uteshop/user/orderpaypal/success",
				            "http://localhost:8082/uteshop/user/orderpaypal/cancel");
				    if (approveLink != null) {
				        // Chuyển hướng người dùng đến giao diện PayPal
						OrderModel order = new OrderModel(user.getId(),shippingcompany_id,currentDate,"Order Placed",totalmoney,status,status_payment,shippingaddress,timestamp,timestamp);
                        session.setAttribute("orderpaypal", order);
				        resp.sendRedirect(approveLink);
				        return;
				    } else {
				        session.setAttribute("error", "PayPal payment initialization failed.");
				        System.out.println("paypal that bai");
				        resp.sendRedirect("/uteshop/views/error.jsp");
				        return;
				    }

				}
				OrderModel order = new OrderModel(user.getId(),shippingcompany_id,currentDate,"Order Placed",totalmoney,status,status_payment,shippingaddress,timestamp,timestamp);

			
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
					orderedProduct.setStatus("Pending"); //chỉnh sửa đây nè 
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
					orderedProduct.setStatus("Pending");
					orderdetail_service.insertOrderedProduct(orderedProduct);
				}
				}
				
				session.removeAttribute("totalMoney");
				session.removeAttribute("shippingcompanyId"); //
			
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
