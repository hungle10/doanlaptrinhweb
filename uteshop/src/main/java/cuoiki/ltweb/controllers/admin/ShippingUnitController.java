package cuoiki.ltweb.controllers.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.ShippingCompanyModel;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin/shippingunits","/admin/shippingunit/add","/admin/shippingunit/update","/admin/shippingunit/delete"})
public class ShippingUnitController extends HttpServlet{
   IShippingCompanyService shipping_unit_service = new IShippingCompanyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		
		if(path.contains("update"))
		{
			ShippingCompanyModel shippingunit = shipping_unit_service.findById(Long.valueOf(req.getParameter("sid")));
			req.setAttribute("shippingunit", shippingunit);
			req.getRequestDispatcher("/views/admin/update-shippingunit.jsp").forward(req, resp);
			return;

		}
		
		List<ShippingCompanyModel> shipping_unit_list = shipping_unit_service.getAllShippingCompany();
		req.setAttribute("shippingList", shipping_unit_list);
		req.getRequestDispatcher("/views/admin/display-shippingunit.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.contains("add")) {
			String shippingunit_name = req.getParameter("shippingunit_name");
			String shippingunit_email = req.getParameter("shippingunit_email");
			String shippingunit_contact_number = req.getParameter("shippingunit_contact_number");
			String shippingunit_address = req.getParameter("shippingunit_address");
			String shippingunit_delivery_feeStr = req.getParameter("shippingunit_delivery_fee");
			float shippingunit_delivery_fee = Float.valueOf(shippingunit_delivery_feeStr);
			long millis = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
			timestamp.setNanos(0);
			ShippingCompanyModel shippingunit = new ShippingCompanyModel(shippingunit_name,shippingunit_contact_number,shippingunit_email,shippingunit_address,shippingunit_delivery_fee,timestamp,timestamp);
			
			shipping_unit_service.insert(shippingunit);
			resp.sendRedirect("/uteshop/admin/shippingunits");
			return;

		}
		if(path.contains("update")) {
			String shippingunit_idStr = req.getParameter("sid");
			long shippingunit_id = Long.valueOf(shippingunit_idStr);
			String shippingunit_name = req.getParameter("shippingunit_name");
			String shippingunit_email = req.getParameter("shippingunit_email");
			String shippingunit_contact_number = req.getParameter("shippingunit_contact_number");
			String shippingunit_address = req.getParameter("shippingunit_address");
			String shippingunit_delivery_feeStr = req.getParameter("shippingunit_delivery_fee");
			float shippingunit_delivery_fee = Float.valueOf(shippingunit_delivery_feeStr);
			
			long millis = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
			timestamp.setNanos(0);
			ShippingCompanyModel shippingunit = new ShippingCompanyModel(shippingunit_id,shippingunit_name,shippingunit_contact_number,shippingunit_email,shippingunit_address,shippingunit_delivery_fee,timestamp);
			shipping_unit_service.update(shippingunit);
			
			 resp.sendRedirect("/uteshop/admin/shippingunits");
			return;
		}
		if(path.contains("delete")) {
			String shippingunit_idStr = req.getParameter("sid");
			long shippingunit_id = Long.valueOf(shippingunit_idStr);
			ShippingCompanyModel shipping_unit = shipping_unit_service.findById(shippingunit_id);
			
			shipping_unit_service.delete(shippingunit_id);
			 resp.sendRedirect("/uteshop/admin/shippingunits");
				return;
 		}
	}

}
