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

	private static final String SECRET_KEY = "mySecretKeyForJWT1234567890!@#$%^&*()"; // Khóa bí mật JWT
	IUserDAO user = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			UserModel u = (UserModel) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getRoleId() == 1) {
				resp.sendRedirect(req.getContextPath() + "/admin/home");
				return;
			} else if (u.getRoleId() == 2) {
				resp.sendRedirect(req.getContextPath() + "/vendor/home");
				return;
			} else {
				resp.sendRedirect(req.getContextPath() + "/home");
				return;
			}
		}
		if (session != null && session.getAttribute("accountfb") != null) {
			resp.sendRedirect(req.getContextPath() + "/home");
			return;
		}

		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("jwtToken")) {
					String username = decodeJWT(cookie.getValue());
					UserModel u = user.findByUserName(username);
					if (u.getRoleId() == 1) {
						resp.sendRedirect(req.getContextPath() + "/admin/home");
						return;
					} else if (u.getRoleId() == 2) {
						resp.sendRedirect(req.getContextPath() + "/vendor/home");
						return;
					} else {
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					}
				}
				if (cookie.getName().equals("fbToken")) {
					resp.sendRedirect(req.getContextPath() + "/home");
					return;
				}
			}
		}
	}

	public static String decodeJWT(String jwt) {
		Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

		return claims.getSubject(); // returns the username
	}

}
