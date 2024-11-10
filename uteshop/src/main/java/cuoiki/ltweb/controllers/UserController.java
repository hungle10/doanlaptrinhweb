package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/v1/api/getUserInfo")
public class UserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
        if (path.contains("/v1/api/getUserInfo")) {
   		 String userIdStr = req.getParameter("user_id");
   		 System.out.println(userIdStr);
   		 IUserService user_service = new IUserServiceImpl();
   		 long userid = Long.parseLong(userIdStr);

   	        // Giả sử bạn có phương thức để lấy thông tin người dùng từ cơ sở dữ liệu
   	        UserModel user = user_service.findById(userid);

   	        resp.setContentType("application/json");
   	        resp.setCharacterEncoding("UTF-8");

   	        PrintWriter out = resp.getWriter();
   	        JSONObject json = new JSONObject();

   	        if (user != null) {
   	            json.put("name", user.getFullname());
   	            json.put("image", user.getImage());
   	        } else {
   	            json.put("error", "User not found");
   	        }

   	        out.print(json.toString());
   	        out.flush();
        
        } 
		
	    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
