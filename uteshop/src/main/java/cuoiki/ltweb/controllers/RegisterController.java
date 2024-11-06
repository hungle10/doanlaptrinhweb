package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import java.sql.Date;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String phonenumber = req.getParameter("phonenumber");
		String address = req.getParameter("address");
		String dateOfBirthStr = req.getParameter("dateOfBirth");
	    Date dateOfBirth = java.sql.Date.valueOf(dateOfBirthStr);

		IUserService service = new IUserServiceImpl();

	   if (service.checkExistEmail(email)) {
			System.out.print("that bai email");
			//alertMsg = "Email đã tồn tại!";
			//req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		if (service.checkExistUsername(username)) {
			System.out.print("that bai username");
			//alertMsg = "Tài khoản đã tồn tại!";
			//req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		boolean isSuccess = service.register(username,fullname,phonenumber,address,email,password,true,dateOfBirth,"",3);
		if (isSuccess) {
			// SendMail sm = new SendMail();
			// sm.sendMail(email, "Shopping.iotstar.vn", "Welcome to Shopping. Please Login
			// to use service. Thanks !");
			System.out.print("Thanh cong ");
			//req.setAttribute("alert", alertMsg);
			resp.sendRedirect("/uteshop/views/login.jsp");
		} else {
			System.out.print("that bai ");
			//alertMsg = "System error!";
			//req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/uteshop/views/register.jsp").forward(req, resp);
		}
	}

}
