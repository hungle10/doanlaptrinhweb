package cuoiki.ltweb.controllers.admin;

import java.io.IOException;
import java.util.List;

import cuoiki.ltweb.impl.IUserServiceImpl;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/users","/admin/user/delete","/admin/user/update"})
public class UserController extends HttpServlet{
	IUserService user_service = new IUserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserModel> users = user_service.findAll();
		
		req.setAttribute("userList", users);
		req.getRequestDispatcher("/views/admin/display-user.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("update")) {
			String uidStr = req.getParameter("uid");
			long uid = Long.valueOf(uidStr);
			String isActiveStr = req.getParameter("isActive");
			boolean isActive = Boolean.valueOf(isActiveStr);
			
			UserModel user = user_service.findById(uid);
			user.setIsActive(isActive);
			
		    System.out.println(user.getFullname());
		    System.out.println(user.getIsActive());
			
			user_service.update(user);
			
			resp.sendRedirect("/uteshop/admin/users");
			return;

		}
		if(path.contains("delete")) {
			String uidStr = req.getParameter("uid");
			long uid = Long.valueOf(uidStr);
			
			user_service.delete(uid);
			resp.sendRedirect("/uteshop/admin/users");
			return;
		}
	}
	
	

}
