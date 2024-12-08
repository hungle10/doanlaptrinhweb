package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import cuoiki.ltweb.impl.IOrderServiceImpl;
import cuoiki.ltweb.models.OrderModel;
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String path = req.getServletPath();
	        HttpSession session = req.getSession();

	        if (path.contains("success")) {
	            String orderID = req.getParameter("token");
	            System.out.println("Order ID: " + orderID);
	            // Gọi PayPal để xác nhận thanh toán
	            if (PayPalService.capturePayment(orderID)) {
	            	OrderModel order_paypal = (OrderModel)session.getAttribute("orderpaypal");
	          
	            	boolean isSucess = order_service.insertOrder(order_paypal);
					if(isSucess==true)
					System.out.println("thuyen em di trong paypal");
	                session.setAttribute("order", "success");
	                resp.sendRedirect("/uteshop/views/success.jsp");
	            } else {
	                session.setAttribute("error", "Payment failed. Please try again.");
	                System.out.println("popo");
	                resp.sendRedirect("/uteshop/views/error.jsp");
	            }
	        } else if (path.contains("cancel")) {
	            session.setAttribute("error", "Payment was cancelled.");
	            System.out.println("popo4545");
	            resp.sendRedirect("/uteshop/views/error.jsp");
	        }
	    }
	}
