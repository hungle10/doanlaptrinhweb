package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShippingCompanyModel;
import cuoiki.ltweb.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/checkout","/user/checkout/shippingcaculator" })
public class CheckoutController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IProductService product_service = new IProductServiceImpl();
		
		IShippingCompanyService shipping_unit_service = new IShippingCompanyServiceImpl();
		String path = req.getServletPath();
		
		HttpSession session = req.getSession();
		if (path.contains("shippingcaculator"))
		{
			float cost = 0;
			 String carrierIdStr = req.getParameter("carrier");
             long carrierId = Long.valueOf(carrierIdStr);
             ShippingCompanyModel shippingunit = shipping_unit_service.findById(carrierId);
             cost = shippingunit.getDelivery_fee();
		      /*  // Xử lý tính phí vận chuyển
		        switch (carrier) {
		            case "Viettel":
		                cost = 30000;
		                break;
		            case "NinjaVan":
		                cost = 40000;
		                break;
		            default:
		                cost = 0;
			 
		        }*/
             
		        session.setAttribute("deliverycharge",cost);
		        session.setAttribute("shippingcompanyId", carrierId);
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
		if(req.getParameter("from")!=null) {
		String from = req.getParameter("from");
		if(from.equals("buynow")) {
			session.setAttribute("from", from);
			String pidStr = req.getParameter("pid");
			
			ProductModel prod = product_service.getProductsByProductId(Long.valueOf(pidStr));
			float totalPrice = product_service.getProductPriceAfterDiscount(prod.getDiscount(),prod.getPrice());
			session.setAttribute("productBuyNow",prod);
			session.setAttribute("totalPrice",totalPrice );
			session.setAttribute("totalProduct",1);
			List<ShippingCompanyModel> shippingUnits = shipping_unit_service.getAllShippingCompany();
			req.setAttribute("shippingUnits",  shippingUnits);
			req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
			return;
		}
		}
		
		
		ICartService cart_service = new ICartServiceImpl();
		
		String totalPriceStr = req.getParameter("totalPrice");
		float totalPrice = Float.valueOf(totalPriceStr) ;

		
		UserModel user = (UserModel)session.getAttribute("activeUser");
		
		
		int totalProduct = cart_service.getCartCountByUserId(user.getId());
		
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("totalProduct",totalProduct);
		List<ShippingCompanyModel> shippingUnits = shipping_unit_service.getAllShippingCompany();
		req.setAttribute("shippingUnits",  shippingUnits);
		req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
