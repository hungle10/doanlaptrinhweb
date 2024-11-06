package cuoiki.ltweb.controllers;

import java.io.IOException;

import java.util.List;

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

	
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/home","/forgotPassword"})
public class HomeController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		ICategoryDAO catedao = new CategoryDAOImpl();
		List<CategoryModel> categoryList = catedao.findAll();
		IProductDAO productDao = new ProductDAOImpl();
		IProductService productService = new IProductServiceImpl();
		List<ProductModel> productList = productDao.getAllLatestProducts();
		List<ProductModel> topDeals = productDao.getDiscountedProducts();
		session.setAttribute("categoryList",categoryList);
		session.setAttribute("productList",productList);
		session.setAttribute("topDeals",topDeals);
		
		// đưa ra những product mới tạo
		for(int i=0;i<Math.min(3,productList.size());i++)
		{
			int price_after_discount = productService.getProductPriceAfterDiscount(productList.get(i).getDiscount(),productList.get(i).getPrice());
			productList.get(i).setPrice_after_discount(price_after_discount);
		}
		//đưa ra những product có discount > 30%
		for(int i=0;i<Math.min(4,topDeals.size());i++)
		{
			int price_after_discount = productService.getProductPriceAfterDiscount(topDeals.get(i).getDiscount(),topDeals.get(i).getPrice());
			topDeals.get(i).setPrice_after_discount(price_after_discount);
		}
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//hàm xử lí quên pass gửi qua mail
		/*String username = req.getParameter("username");
		System.out.println(username);
		String email = req.getParameter("email");
		IUserDAO u = new UserDAOImpl();
		UserModel user = u.findByUserName(username);
		if(user.getEmail().equals(email))
		{
			resp.setContentType("text/html");

    	    // Create a PrintWriter object to write the HTML content
    	    PrintWriter out = resp.getWriter();
    	    
    	    // Write the HTML code for the greeting message
    	    out.println("This is your password"+user.getPassword());
		}
		else
		{
			resp.setContentType("text/html");

    	    // Create a PrintWriter object to write the HTML content
    	    PrintWriter out = resp.getWriter();
    	    out.println("Username không khớp với mật khẩu!");
		}*/
	}
}
