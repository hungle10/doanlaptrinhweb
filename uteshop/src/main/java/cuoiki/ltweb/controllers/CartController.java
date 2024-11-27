package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.CartModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.ICartService;
import cuoiki.ltweb.services.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/user/cart","/user/addcart","/user/cartoperation"})
public class CartController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserModel user = (UserModel)session.getAttribute("activeUser");
	
		String path = req.getServletPath();
		if(path.contains("cartoperation")) {
			ICartService cart_service = new ICartServiceImpl();
			IProductService product_service = new IProductServiceImpl();
			int cid =Integer.parseInt(req.getParameter("cid"));
			int opt =Integer.parseInt(req.getParameter("opt"));
			
			int qty =  cart_service.getQuantityByCartId(cid);
			long pid =  cart_service.getProductId(cid);
			
			//số lượng product trong kho :))
			int quantity = product_service.getProductQuantityById(pid);
			
			
			//tăng số lượng product trong cart -> giảm số lượng product trong kho
			if(opt == 1) {
				if(quantity > 0) {
					cart_service.updateQuantity(cid, qty+1);
					//updating(decreasing) quantity of product in database
					 product_service.updateQuantity(pid,  product_service.getProductQuantityById(pid) - 1);
					 session.setAttribute("cartCount",cart_service.getCartCountByUserId(user.getId()));
					resp.sendRedirect("/uteshop/user/cart");
					return;
					
				}else {
					String message = "Product out of stock! error";
					session.setAttribute("message", message);
					resp.sendRedirect("/uteshop/user/cart");
					return;
				}
				
			}else if(opt == 2) {
				cart_service.updateQuantity(cid, qty-1);
				
				//updating(increasing) quantity of product in database
				product_service.updateQuantity(pid,  product_service.getProductQuantityById(pid) + 1);
				session.setAttribute("cartCount",cart_service.getCartCountByUserId(user.getId()));
				resp.sendRedirect("/uteshop/user/cart");
				return;
				
			}else if(opt == 3) {
				//remove product khỏi cart đó
				cart_service.removeProductInCart(cid);
				String message = "Product removed from cart! success";
				session.setAttribute("message", message);
				
				//updating quantity of product in database
				//adding all the product qty back to database
				product_service.updateQuantity(pid, product_service.getProductQuantityById(pid) + qty);
				session.setAttribute("cartCount",cart_service.getCartCountByUserId(user.getId()));
				resp.sendRedirect("/uteshop/user/cart");
				return;
			}
			
		}
	
		ICartService cart_service = new ICartServiceImpl();
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
		String message = "";
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		UserModel user = (UserModel)session.getAttribute("activeUser");
		if(path.contains("addcart"))
		{
			ICartService cart_service = new ICartServiceImpl();
			IProductService product_service = new IProductServiceImpl();

			long user_id = Long.parseLong(req.getParameter("uid"));
			long product_id =Long.parseLong(req.getParameter("pid"));
			
			int quantity = cart_service.getQuantity(user_id,product_id);
			if (quantity == 0) {
				CartModel cart = new CartModel(user_id,product_id, quantity + 1);
				cart_service.addToCart(cart);
				 session.setAttribute("cartCount",cart_service.getCartCountByUserId(user.getId()));
			    message = "Product is added to cart successfully!";
				
			}else {
				int cid = cart_service.getIdCartByUserIdAndProductId(user_id, product_id);
				cart_service.updateQuantity(cid, quantity+1);
				message = "Product quantity is increased!";
			}
			//updating quantity of product in database
			
			product_service.updateQuantity(product_id, product_service.getProductQuantityById(product_id) - 1);
			session.setAttribute("message", message);
			resp.sendRedirect("/uteshop/view/product?pid="+product_id);
			
		}
	}

}
