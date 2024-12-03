package cuoiki.ltweb.controllers.admin;

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
import jakarta.servlet.http.Part;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.CategoryModel;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/categories","/admin/category/update","/admin/category/delete","/admin/category/add"})
@MultipartConfig
public class CategoryController extends HttpServlet{
	ICategoryService category_service = new ICategoryServiceImpl();
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repositorydoanlaptrinhweb\\uteshop\\src\\main\\webapp\\Images";
	public static final String DEFAULT_FILENAME = "default.file";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if(path.contains("category/update")){
			
			String categoryIdStr = req.getParameter("cid");
			long categoryId = Long.valueOf(categoryIdStr);
			CategoryModel category = category_service.getCategoryById(categoryId);
			
			req.setAttribute("category", category);
			req.getRequestDispatcher("/views/admin/update-category.jsp").forward(req, resp);
			return;
		}
	
		List<CategoryModel> category_list = category_service.findAll();
		req.setAttribute("categoryList", category_list);
		req.getRequestDispatcher("/views/admin/display-category.jsp").forward(req, resp);
        return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
		
		if(path.contains("category/update")){
			
			String categoryIdStr = req.getParameter("cid");
			long categoryId = Long.valueOf(categoryIdStr);
			CategoryModel category = category_service.getCategoryById(categoryId);
			
			String category_name = req.getParameter("category_name");
			
			
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
		
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = (List<Part>) req.getParts();
				    
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if (fileName !=  "") {
						
						String partName = part.getName();
						   if ("fileimage".equals(partName)) {
							   image = fileName; // Lưu tên ảnh
							   
					        }
						
						part.write(uploadPath + File.separator + fileName);
					}

				}
			
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
			
				category.setName(category_name);
				category.setImage(image);
				category.setUpdatedAt(timestamp);
		

				category_service.update(category);
				
				req.setAttribute("message", "Category has been updated successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			resp.sendRedirect("/uteshop/admin/categories");

			return;
		}
		if(path.contains("delete")) {
			String categoryIdStr = req.getParameter("cid");
			long category_id = Long.valueOf(categoryIdStr);
			CategoryModel category = category_service.getCategoryById(category_id);
			
			category_service.delete(category);
			resp.sendRedirect("/uteshop/admin/categories");
		}	
		if(path.contains("add")){

		
			String category_name = req.getParameter("category_name");
			
			
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
		
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = (List<Part>) req.getParts();
				    
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if (fileName !=  "") {
						
						String partName = part.getName();
						   if ("fileimage".equals(partName)) {
							   image = fileName; // Lưu tên ảnh
							   
					        }
						
						part.write(uploadPath + File.separator + fileName);
					}

				}
			
				long millis = System.currentTimeMillis();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
				timestamp.setNanos(0);
			
				CategoryModel category = new CategoryModel();
				category.setName(category_name);
				category.setImage(image);
				category.setCreatedAt(timestamp);
				category.setUpdatedAt(timestamp);
		

				category_service.insert(category);
				
				req.setAttribute("message", "Category has been updated successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			resp.sendRedirect("/uteshop/admin/categories");

			return;
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
