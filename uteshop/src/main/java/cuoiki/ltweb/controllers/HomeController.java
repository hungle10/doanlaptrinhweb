package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cuoiki.ltweb.models.CategoryModel;
import cuoiki.ltweb.impl.CategoryDAOImpl;
import cuoiki.ltweb.dao.ICategoryDAO;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.*;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.mail.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/home", "/forgotPassword", "/changePassword","/otpcode","/updateProfile" })
public class HomeController extends HttpServlet {
	ICategoryDAO catedao = new CategoryDAOImpl();
	IProductDAO productDao = new ProductDAOImpl();
	IProductService productService = new IProductServiceImpl();
	IUserService userService = new IUserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		

		HttpSession session = req.getSession();
		List<CategoryModel> categoryList = catedao.findAll();
		List<ProductModel> productList = productDao.getAllLatestProducts();
		
		List<String> topProductId = productService.getProductsIdSoldOver10();
		List<ProductModel> topProduct = new ArrayList<ProductModel>();
		for (String productId : topProductId) {
		    System.out.println("Product ID: " + productId);
		    ProductModel product = productService.getProductsByProductId(Long.valueOf(productId));
		    int price_after_discount = productService.getProductPriceAfterDiscount(product.getDiscount(),product.getPrice());
		    product.setPrice_after_discount(price_after_discount);
		    topProduct.add(product);
		}

		

		// đưa ra những product mới tạo
		for (int i = 0; i < Math.min(3, productList.size()); i++) {
			int price_after_discount = productService.getProductPriceAfterDiscount(productList.get(i).getDiscount(),
					productList.get(i).getPrice());
			productList.get(i).setPrice_after_discount(price_after_discount);
		}
	/*	// đưa ra những product có discount > 30%
		for (int i = 0; i < Math.min(4, topDeals.size()); i++) {
			int price_after_discount = productService.getProductPriceAfterDiscount(topDeals.get(i).getDiscount(),
					topDeals.get(i).getPrice());
			topDeals.get(i).setPrice_after_discount(price_after_discount);
		}*/
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("productList", productList);
		session.setAttribute("topProductSold",topProduct);
		
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// hàm xử lí quên pass gửi qua mail
		
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		if (path.contains("forgotPassword")) {
			String email = req.getParameter("email");
			if (userService.checkExistEmail(email)) {
	
				Random rand = new Random();
				int max = 99999, min = 10000;
				int otp = rand.nextInt(max - min + 1) + min;
				
				session.removeAttribute("email");
				session.setAttribute("otp", otp);
				session.setAttribute("email", email);
				MailMessenger.sendOtp(email, otp);

				String message = "We'ev sent a password reset code to " + email + "success";
				session.setAttribute("message", message);
				resp.sendRedirect("/uteshop/views/otp_code.jsp");
			}else
			{
				String message = "Email not found! Try with another email! error";
				session.setAttribute("message", message);
				resp.sendRedirect("/uteshop/views/forgot_password.jsp");
				return;
			}
		}
		if (path.contains("otpcode")) {
			String codeStr = req.getParameter("code");
			int code = Integer.parseInt(codeStr);
			int otp = (int)session.getAttribute("otp");
			if(code == otp) {
				session.removeAttribute("otp");
				resp.sendRedirect("/uteshop/views/change_password.jsp");
			}else {
				String message = "Invalid verification code entered! error";
				session.setAttribute("message", message);
				resp.sendRedirect("/uteshop/views/otp_code.jsp");
				return;
			}

		}
		if(path.contains("changePassword"))
		{
			String password = req.getParameter("password");
			String email =(String)session.getAttribute("email");
			System.out.println(email+"56565656");
			UserModel user = userService.findByEmail(email);
			System.out.println(user.getFullname());
			System.out.println(user.getPassword());
			user.setPassword(password);
			System.out.println(user.getPassword());
			userService.update(user);
			session.removeAttribute("email");
			String message = "Password updated successfully!";
			session.setAttribute("message", message);
			resp.sendRedirect("/uteshop/views/login.jsp");
		}
		/*
		 * String username = req.getParameter("username"); System.out.println(username);
		 * String email = req.getParameter("email"); IUserDAO u = new UserDAOImpl();
		 * UserModel user = u.findByUserName(username);
		 * if(user.getEmail().equals(email)) { resp.setContentType("text/html");
		 * 
		 * // Create a PrintWriter object to write the HTML content PrintWriter out =
		 * resp.getWriter();
		 * 
		 * // Write the HTML code for the greeting message
		 * out.println("This is your password"+user.getPassword()); } else {
		 * resp.setContentType("text/html");
		 * 
		 * // Create a PrintWriter object to write the HTML content PrintWriter out =
		 * resp.getWriter(); out.println("Username không khớp với mật khẩu!"); }
		 */
		if(path.contains("updateProfile")) {
			
		}
	}
}
