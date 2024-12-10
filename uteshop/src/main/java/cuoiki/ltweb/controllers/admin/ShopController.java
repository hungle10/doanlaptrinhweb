package cuoiki.ltweb.controllers.admin;

import java.io.IOException;
import java.util.List;

import cuoiki.ltweb.impl.IShopServiceImpl;
import cuoiki.ltweb.impl.IUserServiceImpl;
import cuoiki.ltweb.mail.MailMessenger;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.IShopService;
import cuoiki.ltweb.services.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/shops","/admin/shop/update"})
public class ShopController extends HttpServlet{
	IShopService shop_service = new IShopServiceImpl();
	IUserService user_service = new IUserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ShopModel> shopList = shop_service.findAll();
		req.setAttribute("shopList", shopList);
		req.getRequestDispatcher("/views/admin/display-shop.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		
		String shopIdStr = req.getParameter("shopid");
		long shopId = Long.valueOf(shopIdStr);
		String statusStr = req.getParameter("status");
		boolean status = Boolean.valueOf(statusStr);
		
		ShopModel shop = shop_service.findById(shopId);
		
		UserModel user = user_service.findById(shop.getVendor_id());
		
		shop.setIs_active(status);
		long millis = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
		timestamp.setNanos(0);
		shop.setUpdated_at(timestamp);
		shop_service.update(shop);
		
		if(status) {
		MailMessenger.RegisteredShopOwnerAccepted(user.getUsername(),user.getEmail(),shop.getName());
		}
		else {
		MailMessenger.RegisteredShopOwnerPended(user.getUsername(),user.getEmail(),shop.getName());
		}
			
		resp.sendRedirect("/uteshop/admin/shops");
		return;
	}
	

}
