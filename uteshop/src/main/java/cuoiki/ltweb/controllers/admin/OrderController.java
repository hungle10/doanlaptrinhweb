package cuoiki.ltweb.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/order/update","/admin/order/delete","/admin/order"})
public class OrderController extends HttpServlet{
	IOrderService order_service = new IOrderServiceImpl();
	IOrderDetailService order_detail_service = new IOrderDetailServiceImpl();
	IProductService product_service = new IProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<OrderModel> list_order = order_service.getAllOrders();
		for (OrderModel order : list_order) {
			
			List<OrderDetailModel> list_order_detail = order_detail_service.getAllOrderedProduct(order.getId());
			for(OrderDetailModel order_detail : list_order_detail) {
			ProductModel product = product_service.getProductsByProductId(order_detail.getProductId());
			order_detail.setProduct(product);
			}
			order.setOrder_detail_list(list_order_detail);
			
		}
		req.setAttribute("orderList", list_order);
		req.getRequestDispatcher("/views/admin/display-order.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     String path = req.getServletPath();
	     if(path.contains("update")) {
	    	 if(req.getParameter("odid")!=null) {
	    		 String order_detail_id_Str = req.getParameter("odid");
	    		 String status = req.getParameter("status");
		    	 order_detail_service.updateOrderDetail(Long.valueOf(order_detail_id_Str), status);

	    	 }
	    	 if(req.getParameter("oid")!=null) {
	    	 String order_id_Str = req.getParameter("oid");
	    	 String payment_status = req.getParameter("payment_status"); 
	    	 order_service.updateOrder(Long.valueOf(order_id_Str), payment_status);
	  
	    	 }
	    	 resp.sendRedirect("/uteshop/admin/order");
	    	 return;
	     }
	     if(path.contains("delete")) {
	    	 String order_detail_id_Str = req.getParameter("odid");
	    	 order_detail_service.delete(Long.valueOf(order_detail_id_Str));
	    	 System.out.println("goi delete");
	    	 resp.sendRedirect("/uteshop/admin/order");
	    	 return;

	     }
	}

}
