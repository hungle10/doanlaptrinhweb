package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import cuoiki.ltweb.impl.ICartServiceImpl;
import cuoiki.ltweb.impl.IOrderDetailServiceImpl;
import cuoiki.ltweb.impl.IOrderServiceImpl;
import cuoiki.ltweb.impl.IProductServiceImpl;
import cuoiki.ltweb.impl.IShippingCompanyServiceImpl;
import cuoiki.ltweb.mail.MailMessenger;
import cuoiki.ltweb.models.CartModel;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.paypal.PayPalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.services.*;

@WebServlet(urlPatterns = { "/user/orderpaypal/success", "/user/orderpaypal/cancel" })
public class PayPalCallbackController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IOrderService order_service = new IOrderServiceImpl();
	IShippingCompanyService shippingcompany_service = new IShippingCompanyServiceImpl();
	ICartService cart_service = new ICartServiceImpl();
	IOrderDetailService orderdetail_service = new IOrderDetailServiceImpl();
	IProductService product_service = new IProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String path = req.getServletPath();
	     HttpSession session = req.getSession();
	    UserModel user = (UserModel) session.getAttribute("activeUser");


	        if (path.contains("success")) {
	            String orderID = req.getParameter("token");
	            System.out.println("Order ID: " + orderID);
	            // Gọi PayPal để xác nhận thanh toán
	            if (PayPalService.capturePayment(orderID)) {
	            	OrderModel order_paypal = (OrderModel)session.getAttribute("orderpaypal");
	          
	            	boolean isSucess = order_service.insertOrder(order_paypal);
					if(isSucess==true)
					System.out.println("thuyen em di trong paypal");
					OrderModel order_added = order_service.getOrderByUserIdAndCreatedAt(user.getId(), order_paypal.getCreatedAt());

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
					session.removeAttribute("orderpaypal");
					session.removeAttribute("deliverycharge"); //remove luôn cái thuộc tính này đã được set bên checkout controller , phục vụ cho giao diện

					//removing all product from cart after successful order
					cart_service.removeAllProductInCartByUserId(user.getId());
					session.setAttribute("cartCount",0);
					
					session.setAttribute("order", "success");
					MailMessenger.successfullyOrderPlaced(user.getUsername(),user.getEmail(),Long.toString(order_added.getId()),order_added.getOrderdate().toString());
				    resp.sendRedirect("/uteshop/views/index.jsp");
				    

	            } else {
	                session.setAttribute("orderfail", "fail");
	                resp.sendRedirect("/uteshop/views/index.jsp");
	            }
	        } else if (path.contains("cancel")) {
	        
	            session.setAttribute("orderpaymentcancel", "Payment was cancelled.");
                resp.sendRedirect("/uteshop/views/index.jsp");
	        }
	    }
	}
