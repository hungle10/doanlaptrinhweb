package cuoiki.ltweb.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/checkout","/user/checkout/shippingcaculator" })
public class CheckoutController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		
		if (path.contains("shippingcaculator"))
		{
			float cost = 0;
			 String carrier = req.getParameter("carrier");

		        // Xử lý tính phí vận chuyển
		        switch (carrier) {
		            case "Viettel":
		                cost = 30000;
		                break;
		            case "NinjaVan":
		                cost = 40000;
		                break;
		            default:
		                cost = 0;
		        }
		        session.setAttribute("deliverycharge",cost);
		        session.setAttribute("shippingcompany", carrier);
		        float totalPrice = (Float)session.getAttribute("totalPrice");
		        float totalMoney = totalPrice+cost;
		        session.setAttribute("totalMoney",totalMoney);
		        System.out.println(totalMoney);
		        // Trả về kết quả dạng JSON
		        resp.setContentType("application/json");
		        resp.getWriter().write("{\"cost\":" + cost + "}");
		        // sau khi thanh toán nhớ remove thuộc tính này
		        return;
		       
		}
		ICartService cart_service = new ICartServiceImpl();
		
		String totalPriceStr = req.getParameter("totalPrice");
		float totalPrice = Float.valueOf(totalPriceStr) ;

		
		UserModel user = (UserModel)session.getAttribute("activeUser");
		
		String from = req.getParameter("from");
		
		int totalProduct = cart_service.getCartCountByUserId(user.getId());
		
		session.setAttribute("from",from);
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("totalProduct",totalProduct);
		req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
