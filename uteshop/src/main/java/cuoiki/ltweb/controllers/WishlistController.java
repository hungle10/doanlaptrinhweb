package cuoiki.ltweb.controllers;



import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/user/wishlist")
public class WishlistController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int uid = Integer.parseInt(req.getParameter("uid"));
		int pid = Integer.parseInt(req.getParameter("pid"));
		String op = req.getParameter("op");

	    IWishlistDAO wishlistDao = new WishlistDAOImpl();
		if(op.trim().equals("add"))
		{
			WishlistModel wishlist = new WishlistModel(uid, pid);
			wishlistDao.addToWishlist(wishlist);
			resp.sendRedirect("/uteshop/user/products");
		}else if(op.trim().equals("remove"))
		{
			wishlistDao.deleteWishlist(uid, pid);
			resp.sendRedirect("/uteshop/user/products");
		}else if(op.trim().equals("delete"))
		{
			wishlistDao.deleteWishlist(uid, pid);
			resp.sendRedirect("/uteshop/user/profile");
	}
	
}
}
