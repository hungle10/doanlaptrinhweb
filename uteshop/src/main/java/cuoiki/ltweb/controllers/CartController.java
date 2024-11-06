package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.CartModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/cart"})
public class CartController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserModel user = (UserModel)session.getAttribute("activeUser");
		ICartServiceImpl cart_service = new ICartServiceImpl();
		List<CartModel> listOfCart = cart_service.getCartListByUserId(user.getId());
		List<ProductModel> listOfProduct = new ArrayList<ProductModel>();
	    IProductService product_service = new IProductServiceImpl();
	    float totalPrice = 0;
	    
	    if(listOfCart!=null) {
	    	for (CartModel cart : listOfCart) {
	    		ProductModel product = product_service.getProductsByProductId(cart.getProductId());
	    		product.setPrice_after_discount(product_service.getProductPriceAfterDiscount(product.getDiscount(), product.getPrice()));
	    		listOfProduct.add(product);
	    		totalPrice += cart.getQuantity() * product.getPrice_after_discount();
	    	}
	    }

		req.setAttribute("listOfCart", listOfCart);
		req.setAttribute("listOfProductInCart", listOfProduct);
		req.setAttribute("totalPrice",totalPrice);
		req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
