package cuoiki.ltweb.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/comment","/user/comment/add", "/user/comment/edit",
		"/user/comment/remove" })
@MultipartConfig
public class CommentController extends HttpServlet {
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repositorydoanlaptrinhweb\\uteshop\\src\\main\\webapp\\uploads";
	public static final String DEFAULT_FILENAME = "default.file";
	
	ICommentService comment_service = new ICommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		IUserService user_service = new IUserServiceImpl();
		List<CommentModel> list_comment = comment_service.getAllComments();
		for (CommentModel comment : list_comment) {
			UserModel user = user_service.findById(comment.getUser_id());
			comment.setUsername(user.getUsername());
		}

		HttpSession session = req.getSession();
		session.setAttribute("commentList", list_comment);
		req.getRequestDispatcher("/uteshop/views/viewDetailProduct.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String path = req.getServletPath();
		if (path.contains("add")) {
			System.out.print("Cuc cu 2222");
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try { 
				String fileName = "";
				String image = "";
				String video = "";
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = (List<Part>) req.getParts();
				    
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if (fileName !=  "") {
						
						String partName = part.getName();
						   if ("fileimage".equals(partName)) {
							   image = fileName; // Lưu tên ảnh
							   
					        } else if ("filevideo".equals(partName)) {
					          
					        	video = fileName; // Lưu tên video
					        }
						part.write(uploadPath + File.separator + fileName);
					}

				}
				UserModel user = (UserModel)session.getAttribute("activeUser");
				String product_idStr = req.getParameter("pid");
				long product_id = Long.valueOf(product_idStr);
				String comment_text = req.getParameter("comment");
			
				
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
				
				CommentModel md = new CommentModel(user.getId(),product_id,comment_text,image,video,timestamp,timestamp);
				comment_service.insert(md);
				
				req.setAttribute("message", "Comment has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			resp.sendRedirect("/uteshop/view/product?pid=" + req.getParameter("pid"));
		}
	}
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return DEFAULT_FILENAME;
	}

}

