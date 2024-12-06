package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.IOrderDAO;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;

public class OrderDAOImpl extends DBConnectSQLServer implements IOrderDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	
	@Override
	public boolean insertOrder(OrderModel order) {
		boolean isSuccess = true;
		try {
			conn = super.getConnection();
			String query = "insert into orders (user_id,shippingcompany_id,order_date,status, total_money,payment_method,payment_status,shipping_address,created_at,updated_at) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			
			psmt.setLong(1,order.getUserid());
			psmt.setLong(2, order.getShippingcompanyid());
			psmt.setDate(3, order.getOrderdate());
			psmt.setString(4, order.getStatus());
			psmt.setFloat(5, order.getTotalmoney());
			psmt.setString(6, order.getPayment_method());
			psmt.setString(7, order.getPayment_status());
			psmt.setString(8, order.getShipping_address());
			psmt.setTimestamp(9, order.getCreatedAt());
			psmt.setTimestamp(10, order.getUpdatedAt());
			
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
	@Override
	public OrderModel getOrderByUserIdAndCreatedAt(long user_id,Timestamp created_at) {
		
		OrderModel order = new OrderModel();
		try {
			conn = super.getConnection();
			String query = "SELECT * FROM orders WHERE user_id = ? and created_at = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			System.out.println("trong ham15");
			psmt.setLong(1, user_id);
			psmt.setTimestamp(2,created_at);
			ResultSet rs = psmt.executeQuery();
			System.out.println("trong ham4");
			rs.next();
			order.setId(rs.getLong("id"));
			order.setUserid(rs.getLong("user_id"));
			order.setShippingcompanyid(rs.getLong("shippingcompany_id"));
			order.setOrderdate(rs.getDate("order_date"));
			order.setStatus(rs.getString("status"));
			order.setTotalmoney(rs.getFloat("total_money"));
			order.setPayment_method(rs.getString("payment_method"));
			order.setPayment_status(rs.getString("payment_status"));
			order.setCreatedAt(rs.getTimestamp("created_at"));
			order.setUpdatedAt(rs.getTimestamp("updated_at"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
	

	@Override
	public List<OrderModel> getOrders(long user_id) {
	    List<OrderModel> orders = new ArrayList<>();
	    try {
	        conn = super.getConnection();
	        String query = "SELECT * FROM orders WHERE user_id = ?";
	        PreparedStatement psmt = this.conn.prepareStatement(query);
	        
	        psmt.setLong(1, user_id);
	        
	        ResultSet rs = psmt.executeQuery();
	        
	        while (rs.next()) {
	            OrderModel order = new OrderModel();
	            order.setId(rs.getLong("id"));
	            order.setUserid(rs.getLong("user_id"));
	            order.setShippingcompanyid(rs.getLong("shippingcompany_id"));
	            order.setOrderdate(rs.getDate("order_date"));
	            order.setStatus(rs.getString("status"));
	            order.setTotalmoney(rs.getFloat("total_money"));
	            order.setPayment_method(rs.getString("payment_method"));
	            order.setPayment_status(rs.getString("payment_status"));
	            order.setCreatedAt(rs.getTimestamp("created_at"));
	            order.setUpdatedAt(rs.getTimestamp("updated_at"));
	            orders.add(order);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return orders;
	}
	
	
	@Override
	public List<OrderModel> getAllOrders() {
	    List<OrderModel> orders = new ArrayList<>();
	    try {
	        conn = super.getConnection();
	        String query = "SELECT * FROM orders";
	        PreparedStatement psmt = this.conn.prepareStatement(query);
	        
	  
	        ResultSet rs = psmt.executeQuery();
	        
	        while (rs.next()) {
	            OrderModel order = new OrderModel();
	            order.setId(rs.getLong("id"));
	            order.setUserid(rs.getLong("user_id"));
	            order.setShippingcompanyid(rs.getLong("shippingcompany_id"));
	            order.setOrderdate(rs.getDate("order_date"));
	            order.setStatus(rs.getString("status"));
	            order.setTotalmoney(rs.getFloat("total_money"));
	            order.setPayment_method(rs.getString("payment_method"));
	            order.setPayment_status(rs.getString("payment_status"));
	            order.setCreatedAt(rs.getTimestamp("created_at"));
	            order.setUpdatedAt(rs.getTimestamp("updated_at"));
	            orders.add(order);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return orders;
	}
	
	@Override
	public void updateOrder(long id, String status) {

		try {
			conn = super.getConnection();
			String query = "update orders set payment_status = ? where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setString(1, status);
			psmt.setLong(2, id);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	

}