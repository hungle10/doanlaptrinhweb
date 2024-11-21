package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import cuoiki.ltweb.dao.IOrderDAO;
import cuoiki.ltweb.models.OrderModel;

public class OrderDAOImpl implements IOrderDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	
	@Override
	public boolean insertOrder(OrderModel order) {
		boolean isSuccess = true;
		try {
			String query = "insert into order (user_id,shippingcompany_id,order_date,status, total_money,payment_method,payment_status,created_at,updated_at) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			
			psmt.setLong(1,order.getUserid());
			psmt.setLong(2, order.getShippingcompanyid());
			psmt.setDate(3, order.getOrderdate());
			psmt.setString(4, order.getStatus());
			psmt.setFloat(5, order.getTotalmoney());
			psmt.setString(6, order.getPayment_method());
			psmt.setString(7, order.getStatus());
			psmt.setTimestamp(8, order.getCreatedAt());
			psmt.setTimestamp(9, order.getUpdatedAt());
			
			int affectedRows = psmt.executeUpdate();

	        if (affectedRows == 0) {
	        	isSuccess = false;
	            throw new SQLException("Insertion failed, no rows affected.");
	            
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	

}
