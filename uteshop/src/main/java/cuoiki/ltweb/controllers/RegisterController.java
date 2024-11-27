package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.mail.MailMessenger;

import java.sql.Date;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/register","/register/otpcode"})
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
		

		IUserService service = new IUserServiceImpl();
		
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		if (path.contains("otpcode")) {
			String codeStr = req.getParameter("code");
			int code = Integer.parseInt(codeStr);
			int otp = (int)session.getAttribute("otp");
			if(code == otp) {
				session.removeAttribute("otp");
				String username =(String)session.getAttribute("username");
				session.removeAttribute("username");
				String fullname = (String)session.getAttribute("fullname");
				session.removeAttribute("fullname");
				String phonenumber = (String)session.getAttribute("phonenumber");
				session.removeAttribute("phonenumber");
				String address = (String)session.getAttribute("address");
				session.removeAttribute("address");
				String email = (String)session.getAttribute("email");
				session.removeAttribute("email");
				String password = (String)session.getAttribute("password");
				session.removeAttribute("password");
				Date dateOfBirth = (java.sql.Date)session.getAttribute("dateOfBirth");
				session.removeAttribute("dateOfBirth");
				boolean isSuccess = service.register(username,fullname,phonenumber,address,email,password,true,dateOfBirth,"",2); //2 là user
				if (isSuccess) {
					MailMessenger.successfullyRegister(username, email);
					String alertMsg = "Thanh cong";
					req.setAttribute("alert", alertMsg);
					resp.sendRedirect("/uteshop/views/login.jsp");
					return;
				} else {
					System.out.print("that bai ");
					String alertMsg = "System error!";
					req.setAttribute("alert", alertMsg);
					resp.sendRedirect("/uteshop/views/register.jsp");
					return;
				}
			}else {
				String message = "Invalid verification code entered! error";
				session.setAttribute("message", message);
				resp.sendRedirect("/uteshop/views/otp_code_activeAcc.jsp");
				return;
			}

		}
		if (path.contains("register")) {
			String username = req.getParameter("username");
			session.setAttribute("username",username);
			String password = req.getParameter("password");
			session.setAttribute("password",password);
			String email = req.getParameter("email");
			session.setAttribute("email",email);
			String fullname = req.getParameter("fullname");
			session.setAttribute("fullname",fullname);
			String phonenumber = req.getParameter("phonenumber");
			session.setAttribute("phonenumber",phonenumber);
			String address = req.getParameter("address");
			session.setAttribute("address",address);
			String dateOfBirthStr = req.getParameter("dateOfBirth");
		    Date dateOfBirth = java.sql.Date.valueOf(dateOfBirthStr);
		    session.setAttribute("dateOfBirth", dateOfBirth);
			if (service.checkExistEmail(email)) {
				
				String alertMsg = "Email đã tồn tại!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
				return;
			}
			if (service.checkExistUsername(username)) {
				System.out.print("Username đã được đăng kí");
			    String alertMsg = "Tài khoản đã tồn tại!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
				return;
			}
			if (service.checkExistPhoneNumber(phonenumber)) {
				System.out.print("Số điện thoại đã được đăng kí");
			    String alertMsg = "Số điện thoại đã tồn tại!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
				return;
			}
		Random rand = new Random();
		int max = 99999, min = 10000;
		int otp = rand.nextInt(max - min + 1) + min;
		System.out.println(otp);
		session.setAttribute("otp", otp);
		session.setAttribute("email", email);
		MailMessenger.sendOtp(email, otp);

		String message = "We'ev sent a active account code to " + email + "success";
		session.setAttribute("message", message);
		req.getRequestDispatcher("/views/otp_code_activeAcc.jsp").forward(req, resp);
		}
		
	}

}
