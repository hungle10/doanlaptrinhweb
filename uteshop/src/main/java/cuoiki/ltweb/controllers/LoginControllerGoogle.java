package cuoiki.ltweb.controllers;

import java.io.IOException;

import cuoiki.ltweb.utils.GooglePojo;
import cuoiki.ltweb.utils.GoogleUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-google")
public class LoginControllerGoogle extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
	    if (code == null || code.isEmpty()) {
	      RequestDispatcher dis = req.getRequestDispatcher("/views/login.jsp");
	      dis.forward(req, resp);
	    } else {
	      String accessToken = GoogleUtils.getToken(code);
	      GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
	      
	   // In ra thông tin của googlePojo để kiểm tra
	      System.out.println("Google User Info:");
	      System.out.println("ID: " + googlePojo.getId());
	      System.out.println("Name: " + googlePojo.getName());
	      System.out.println("Email: " + googlePojo.getEmail());
	      req.setAttribute("id", googlePojo.getId());
	      req.setAttribute("name", googlePojo.getName());
	      req.setAttribute("email", googlePojo.getEmail());
	      RequestDispatcher dis = req.getRequestDispatcher("/views/indexgg.jsp");
	      dis.forward(req, resp);
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// URL của Facebook OAuth
	    String facebookOAuthUrl = "https://www.facebook.com/dialog/oauth?client_id=2211151999254702&redirect_uri=https://localhost:8443/uteshop/login-facebook";
	    // Kiểm tra nếu đã có JWT trong cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("fbToken")) {
                	resp.sendRedirect(req.getContextPath() + "/waiting");
                    return;
                }
            }
        }
	    // Chuyển hướng tới link OAuth
	    resp.sendRedirect(facebookOAuthUrl);
	}

}
