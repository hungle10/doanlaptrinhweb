package cuoiki.ltweb.controllers;

import java.io.IOException;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.models.*;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.dao.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

//	private static final String SECRET_KEY = "mySecretKeyForJWT1234567890!@#$%^&*()"; // Khóa bí mật JWT
	IUserDAO user = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			UserModel u = (UserModel) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getRoleId() == 1) {
				session.setAttribute("activeAdmin",u);
				resp.sendRedirect(req.getContextPath() + "/admin/home");
				return;
			} else if (u.getRoleId() == 2) {
				session.setAttribute("activeUser",u);
				ICartService cart_service = new ICartServiceImpl();
				int cart_count = cart_service.getCartCountByUserId(u.getId());
				session.setAttribute("cartCount",cart_count);
				resp.sendRedirect(req.getContextPath() + "/home");
				return;
			}
			else {
				session.setAttribute("activeUser",u);
				resp.sendRedirect(req.getContextPath() + "/shipper/home");
				return;
			}
		}

		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					String username = (String) session.getAttribute("username");
					UserModel u = user.findByUserName(username);
					if (u.getRoleId() == 1) {
						session.setAttribute("activeAdmin",u);
						resp.sendRedirect(req.getContextPath() + "/admin/home");
						return;
					} else if (u.getRoleId() == 2) {
						session.setAttribute("activeUser",u);
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					} else {
						session.setAttribute("activeUser",u);
						resp.sendRedirect(req.getContextPath() + "/shipper/home");
						return;
					}
				}
			}
		}
	}

	/*public static String decodeJWT(String jwt) {
		Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

		return claims.getSubject(); // returns the username
	}*/

}
