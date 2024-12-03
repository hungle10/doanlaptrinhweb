package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.*;

public class OrderDetailDAOImpl extends DBConnectSQLServer implements IOrderDetailDAO {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public void insertOrderedProduct(OrderDetailModel ordProduct) {
		try {
			conn = super.getConnection();
			String query = "insert into order_details(order_id,product_id, price,number_of_products,total_money,status) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, ordProduct.getOrderId());
			psmt.setLong(2, ordProduct.getProductId());
			psmt.setFloat(3,ordProduct.getPrice());
			psmt.setInt(4, ordProduct.getNumberOfProducts());
			psmt.setFloat(5, ordProduct.getTotalMoney());
			psmt.setString(6,ordProduct.getStatus());

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<OrderDetailModel>getAllOrderedProduct(long oid){
		List<OrderDetailModel> list = new ArrayList<OrderDetailModel>();
		try {
			conn = super.getConnection();
			String query = "select * from order_details where order_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, oid);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				OrderDetailModel orderProd = new OrderDetailModel();
				orderProd.setId(rs.getLong("id"));
				orderProd.setOrderId(rs.getLong("order_id"));
				orderProd.setProductId(rs.getLong("product_id"));
				orderProd.setPrice(rs.getFloat("price"));
				orderProd.setNumberOfProducts(rs.getInt("number_of_products"));
				orderProd.setTotalMoney(rs.getFloat("total_money"));
				orderProd.setStatus(rs.getString("status"));
				list.add(orderProd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateOrderDetail(long id, String status) {

		try {
			conn = super.getConnection();
			String query = "update order_details set status = ? where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setString(1, status);
			psmt.setLong(2, id);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(long id) {
		String sql = "DELETE order_details where id= ?";
		try {
			conn=super.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setLong(1,id);
	
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		}catch(Exception e)
		{
			
		}
	}

}
