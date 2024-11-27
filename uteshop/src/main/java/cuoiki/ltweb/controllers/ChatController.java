package cuoiki.ltweb.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.ChatModel;
import cuoiki.ltweb.models.UserModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/chat","/user/chat/refresh","/user/chat/saveMessage"})
public class ChatController extends HttpServlet{
	IUserService user_service = new IUserServiceImpl();
	IChatService chat_service = new IChatServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("activeUser");
		String userid = String.valueOf(user.getId());
		req.setAttribute("userid", userid);
		
		
		String path = req.getServletPath();
		System.out.println("123");
		
		 if (path.contains("refresh"))
		 {
			 System.out.println("456");
			 String useridStrReceiver = req.getParameter("useridReceiver");
			 long useridReceiver = Long.parseLong(useridStrReceiver);
			 UserModel user_receiver = user_service.findById(useridReceiver);
			 req.setAttribute("user_receiver",user_receiver);
			 
			 List<ChatModel> chat_list = chat_service.getChatList();
			 req.setAttribute("chat_list",chat_list);
			 
			 List<ChatModel> chat_list_mine = chat_service.getChatListBySenderAndReceiver(user.getId(),user_receiver.getId());
			 req.setAttribute("chat_list_mine",chat_list_mine);
			 List<ChatModel> chat_list_opposite = chat_service.getChatListBySenderAndReceiver(user_receiver.getId(),user.getId());
			 req.setAttribute("chat_list_opposite",chat_list_opposite);
		 }
		 System.out.println("789");
	
		
		List<UserModel> user_list = user_service.findAll();
		req.setAttribute("user_list",user_list);
		
		
		
		System.out.println("101112");
		String vendor_id = req.getParameter("vendorid");
		req.setAttribute("vendorid", vendor_id);
		req.getRequestDispatcher("/views/userchat.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("saveMessage")) {
			 // Đọc JSON từ request body
	        StringBuilder jsonData = new StringBuilder();
	        String line;
	        try (BufferedReader reader = req.getReader()) {
	            while ((line = reader.readLine()) != null) {
	                jsonData.append(line);
	            }
	        }

	        // Parse JSON bằng JSONObject
	        JSONObject jsonObject = new JSONObject(jsonData.toString());
	        String userId = jsonObject.getString("userId");
	        String toUser = jsonObject.getString("toUser");
	        String message = jsonObject.getString("message");
	        
	        long millis = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
			timestamp.setNanos(0);
	        
	        System.out.println(userId);
	        System.out.println(toUser);
	        System.out.println(message);
	        ChatModel chat = new ChatModel(Long.valueOf(userId),Long.valueOf(toUser),message,timestamp);
	        
	        boolean isSuccess = chat_service.addToChatInDB(chat);
	        
	        if (isSuccess == true) {
	        	 System.out.println("add chat to db successs");
	        }
	        else {
	        	 System.out.println("no no no");
	        }
	        
		}
	}
	

}
