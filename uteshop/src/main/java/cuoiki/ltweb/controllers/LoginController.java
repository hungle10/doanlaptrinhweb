package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.mail.MailMessenger;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.entities.*;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
  //  private static final String SECRET_KEY = "mySecretKeyForJWT1234567890!@#$%^&*()"; // Khóa bí mật JWT
    public static final String SESSION_USERNAME = "username";
	public static final String COOKIE_REMEMBER = "username";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");
        System.out.println(remember);

        if ("on".equals(remember)) {
            isRememberMe = true;
        }
        
        String alertMsg = "";
        if (username.isEmpty() || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
			session.setAttribute("message", message);
            //req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	    	resp.sendRedirect("/uteshop/views/login.jsp");
            return;
        }
        System.out.println("helo spkt");
        IUserService service = new IUserServiceImpl();
        
        UserModel user = service.login(username, password);
        System.out.println(user);
        System.out.println("helo tdt");
        if(user != null) {
       if (user.getIsActive() == true ) {
			session.setAttribute("account", user);
            if (isRememberMe) {
                saveRememberMe(resp,username);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting"); // Phần waiting(controller) kiểm tra role của user
        } else {
        	
            alertMsg = "Tài khoản đã bị khóa";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
        }else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
       	}
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("helo dom dom 2222");

        // Kiểm tra nếu action là logout
        if ("logout".equals(action)) {
            getLogOut(req, resp);
            return;
        }
		
	    HttpSession session = req.getSession(false);
	    
		//check session 
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
			}
		
        // Kiểm tra nếu đã có JWT trong cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/waiting");
					return;
				}
            }
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
}
   /* private boolean validateJWTToken(String jwtToken) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/
 /*   private String createJWTToken(String username) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expiryDate = new Date(currentTimeMillis + 30 * 60 * 1000); // JWT hết hạn sau 30 phút
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder() //tạo ra jwt builder thiet lap cac thong tin va chuyen no thanh chuoi
                .setSubject(username)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }*/
    private void saveRememberMe(HttpServletResponse response, String username) {
    	Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
    }
    private void getLogOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	//khi logout xoa session
    	HttpSession session = req.getSession();
		session.removeAttribute("account");
		session.removeAttribute("activeAdmin");
		session.removeAttribute("activeUser");

        // Xóa JWT , fb trong cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        resp.sendRedirect("./login");
    }
}
