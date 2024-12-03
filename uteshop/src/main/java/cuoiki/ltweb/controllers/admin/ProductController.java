package cuoiki.ltweb.controllers.admin;

import java.io.IOException;
import java.util.List;

import cuoiki.ltweb.impl.IProductServiceImpl;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.services.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/products","/admin/product/delete"})
@MultipartConfig
public class ProductController extends HttpServlet{
	IProductService product_service = new IProductServiceImpl();
	ICategoryService category_service = new ICategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		List<ProductModel> productList = product_service.getAllProducts();
		for (ProductModel product : productList) {
			String category_name = category_service.getCategoryName(product.getCategory_id());
			product.setCategory_name(category_name);
		}
		req.setAttribute("productList", productList);
		req.getRequestDispatcher("/views/admin/display-product.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("product/delete")) {
			String prodIdStr = req.getParameter("pid");
			long proId = Long.valueOf(prodIdStr);
			
			product_service.delete(proId);
			resp.sendRedirect("/uteshop/admin/products");
			return;
		}
	}

}
