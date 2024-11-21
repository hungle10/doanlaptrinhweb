package cuoiki.ltweb.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;




import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/order/add"})
public class OrderController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IShippingCompanyService shippingcompany_service = new IShippingCompanyServiceImpl();
		
		
		HttpSession session = req.getSession();
		String from = (String) session.getAttribute("from");
		String paymentmethod = req.getParameter("payementMode");
		String shippingcompany = (String)req.getAttribute("shippingcompany");
		float totalmoney = (float)session.getAttribute("totalMoney");
	
		long shippingcompany_id = shippingcompany_service.getIdShippingCompanyByName(shippingcompany);
		UserModel user = (UserModel) session.getAttribute("activeUser");
		String shippingaddress = user.getAddress(); 
				
		long millis = System.currentTimeMillis();
		Date currentDate = new Date(millis);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
		
		String status = "Order Placed";
	{
//nếu customer thanh toán bằng thẻ
		if (from.trim().equals("cart")) {
			try {
			    

				OrderModel order = new OrderModel(user.getId(),shippingcompany_id,currentDate,"New",totalmoney,paymentmethod,"paid",shippingaddress,timestamp,timestamp);
				IOrderService order_service = new IOrderServiceImpl();
				boolean isSucess = order_service.insertOrder(order);

				CartDao cartDao = new CartDao(ConnectionProvider.getConnection());
				List<Cart> listOfCart = cartDao.getCartListByUserId(user.getUserId());
				OrderedProductDao orderedProductDao = new OrderedProductDao(ConnectionProvider.getConnection());
				ProductDao productDao = new ProductDao(ConnectionProvider.getConnection());
				for (Cart item : listOfCart) {

					Product prod = productDao.getProductsByProductId(item.getProductId());
					String prodName = prod.getProductName();
					int prodQty = item.getQuantity();
					float price = prod.getProductPriceAfterDiscount();
					String image = prod.getProductImages();

					OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, id);
					orderedProductDao.insertOrderedProduct(orderedProduct);
				}
				session.removeAttribute("from");
				session.removeAttribute("totalPrice");
				
				//removing all product from cart after successful order
				cartDao.removeAllProduct();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (from.trim().equals("buy")) {

			try {

				int pid = (int) session.getAttribute("pid");
				Order order = new Order(orderId, status, paymentType, user.getUserId());
				OrderDao orderDao = new OrderDao(ConnectionProvider.getConnection());
				int id = orderDao.insertOrder(order);
				OrderedProductDao orderedProductDao = new OrderedProductDao(ConnectionProvider.getConnection());
				ProductDao productDao = new ProductDao(ConnectionProvider.getConnection());

				Product prod = productDao.getProductsByProductId(pid);
				String prodName = prod.getProductName();
				int prodQty = 1;
				float price = prod.getProductPriceAfterDiscount();
				String image = prod.getProductImages();

				OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, id);
				orderedProductDao.insertOrderedProduct(orderedProduct);
				
				//updating(decreasing) quantity of product in database
				productDao.updateQuantity(pid, productDao.getProductQuantityById(pid) - 1);
				
				session.removeAttribute("from");
				session.removeAttribute("pid");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    session.setAttribute("order", "success");
	    MailMessenger.successfullyOrderPlaced(user.getUserName(), user.getUserEmail(), orderId, new Date().toString());
        response.sendRedirect("index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
