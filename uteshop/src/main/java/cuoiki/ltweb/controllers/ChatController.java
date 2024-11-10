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
import cuoiki.ltweb.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/chat","/user/chat/refresh"})
public class ChatController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IUserService user_service = new IUserServiceImpl();
		String path = req.getServletPath();
		System.out.println("123");
		 if (path.contains("refresh"))
		 {
			 System.out.println("456");
			 String useridStrReceiver = req.getParameter("useridReceiver");
			 long useridReceiver = Long.parseLong(useridStrReceiver);
			 UserModel user_receiver = user_service.findById(useridReceiver);
			 req.setAttribute("user_receiver",user_receiver);
		 }
		 System.out.println("789");
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("activeUser");
		String userid = String.valueOf(user.getId());
		req.setAttribute("userid", userid);
		List<UserModel> user_list = user_service.findAll();
		req.setAttribute("user_list",user_list);
		System.out.println("101112");
		req.getRequestDispatcher("/views/userchat.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
