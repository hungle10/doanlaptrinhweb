package cuoiki.ltweb.controllers;

import java.util.Calendar;
import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;
import cuoiki.ltweb.utils.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.restfb.types.*;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.UserModel;
@WebServlet("/login-facebook")
public class LoginFacebookController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService iuser = new IUserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String code = req.getParameter("code");
		    
		    if (code == null || code.isEmpty()) {
		      RequestDispatcher dis = req.getRequestDispatcher("/views/login.jsp");
		      dis.forward(req, resp);
		    } else {
		      String accessToken = RestFB.getToken(code);
		      User user = RestFB.getUserInfo(accessToken);
		      
		      req.setAttribute("id", user.getId()); //id facebook
		      req.setAttribute("name", user.getName());
		      
		      //tajo session accountfb
		      HttpSession session = req.getSession(true);
			  session.setAttribute("accountfb",user.getId());

			  saveRememberMe(resp, user.getId());
			  
			  //add user bang tai khoan fb

			  Boolean check = iuser.registerByFb(generateUniqueUsername(user.getName()),user.getName(),"","",generateFakeEmail(user.getId()),"",true,generateFakeBirthday(),"",Long.parseLong(user.getId()),0,3);
			  if(check==true)
				  System.out.println("add thanh cong bang fb");
			  else
				  System.out.println("da co tai khoan fb");
			  
			  resp.sendRedirect(req.getContextPath() + "/waiting");

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
	 private void saveRememberMe(HttpServletResponse response, String userfbId) {
		 Cookie cookie = new Cookie("fbToken", userfbId);
	        cookie.setPath("/"); // Cookie có hiệu lực cho toàn bộ ứng dụng
	        cookie.setMaxAge(30 * 60); // 30 phút
	        cookie.setHttpOnly(true); // Ngăn chặn truy cập từ JavaScript
	        cookie.setSecure(true); // Cehỉ gửi cookie qua HTTPS
	        cookie.setAttribute("same_site", "None"); // Đảm bảo cookie có thể được gửi qua các domain khác
	        // Thêm cookie vào response
	        response.addCookie(cookie);
	    }
	 String generateUniqueUsername(String fullname) {
		    return fullname.replaceAll("\\s+", "") + "_" + (int) (Math.random() * 1000);
		}
	 public String generateFakeEmail(String facebookAccountId) {
		    return "fb_" + facebookAccountId + "@placeholder.com";
		}
	 public Date generateFakeBirthday() {
		 Calendar calendar = Calendar.getInstance();

		    // Thiết lập khoảng tuổi từ 18 đến 65
		    int minAge = 18;
		    int maxAge = 65;

		    // Tính toán năm hiện tại và chọn ngẫu nhiên một năm sinh trong khoảng 18-65 năm trước
		    int currentYear = calendar.get(Calendar.YEAR);
		    int birthYear = currentYear - ThreadLocalRandom.current().nextInt(minAge, maxAge + 1);

		    // Thiết lập ngày sinh giả ngẫu nhiên trong năm sinh đã chọn
		    calendar.set(Calendar.YEAR, birthYear);
		    int dayOfYear = ThreadLocalRandom.current().nextInt(1, calendar.getActualMaximum(Calendar.DAY_OF_YEAR) + 1);
		    calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

		    // Chuyển đổi sang java.sql.Date trước khi trả về
		    return new Date(calendar.getTimeInMillis());
		}


}
