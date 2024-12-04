package cuoiki.ltweb.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShippingCompanyModel;
import cuoiki.ltweb.models.ShopModel;

public class ShippingCompanyDAOImpl extends DBConnectSQLServer implements IShippingCompanyDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public long getIdShippingCompanyByName(String companyname) {
		long cid = 0;
		try {
			conn = super.getConnection();
			String query = "select * from shippingcompanys where name = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setString(1, companyname);
		

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				cid = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cid;
	}

	@Override
	public ShippingCompanyModel findById(long id) {
		String sql = "SELECT * FROM shippingcompanys WHERE id = ?";
		ShippingCompanyModel shippingunit = new ShippingCompanyModel();

		try {
		    conn = super.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setLong(1,id); 
		    rs = ps.executeQuery(); 
		    while(rs.next())
			{
		    	shippingunit.setId(rs.getLong("id"));
		    	shippingunit.setName(rs.getString("name"));
		    	shippingunit.setContact_number(rs.getString("contact_number"));
		    	shippingunit.setEmail(rs.getString("email"));
		    	shippingunit.setAddress(rs.getString("address"));
		    	shippingunit.setDelivery_fee(rs.getFloat("delivery_fee"));
		    	shippingunit.setCreatedAt(rs.getTimestamp("created_at"));
		    	shippingunit.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		    return shippingunit;
		    }
		   catch (Exception e) 
		{
		    e.printStackTrace();
		} finally 
		{
		   
		}
		return null;
	}
	@Override
	public List<ShippingCompanyModel> getAllShippingCompany() {
		List<ShippingCompanyModel> list = new ArrayList<ShippingCompanyModel>();
		try {
			conn = super.getConnection();
			String query = "select * from shippingcompanys";
			Statement statement = this.conn.createStatement();

			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				ShippingCompanyModel shippingunit = new ShippingCompanyModel();
				shippingunit.setId(rs.getLong("id"));
		    	shippingunit.setName(rs.getString("name"));
		    	shippingunit.setContact_number(rs.getString("contact_number"));
		    	shippingunit.setEmail(rs.getString("email"));
		    	shippingunit.setAddress(rs.getString("address"));
		    	shippingunit.setDelivery_fee(rs.getFloat("delivery_fee"));
		    	shippingunit.setCreatedAt(rs.getTimestamp("created_at"));
		    	shippingunit.setUpdatedAt(rs.getTimestamp("updated_at"));
				list.add(shippingunit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void update(ShippingCompanyModel shippingunit) {
		String sql = "UPDATE shippingcompanys \r\n"
			    +"SET name=?,contact_number=?,email=?,address=?,delivery_fee=?,updated_at=?\r\n"
				+"WHERE id=?";
		try {
			conn = DBConnectSQLServer.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,shippingunit.getName());
			ps.setString(2,shippingunit.getContact_number());
			ps.setString(3,shippingunit.getEmail());
			ps.setString(4,shippingunit.getAddress());
			ps.setFloat(5,shippingunit.getDelivery_fee());
			ps.setTimestamp(6, shippingunit.getUpdatedAt());
			ps.setLong(7,shippingunit.getId());
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		}catch(Exception e)
		{
			
		}
		
	}
	@Override
	public void insert(ShippingCompanyModel shippingunit) {
		String sql = "INSERT INTO shippingcompanys (name, contact_number, email,address,delivery_fee,created_at, updated_at)\r\n"
				+ "VALUES (?,?,?,?,?,?,?)";
		try
		{
			conn=super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,shippingunit.getName());
			ps.setString(2,shippingunit.getContact_number());
			ps.setString(3,shippingunit.getEmail());
			ps.setString(4,shippingunit.getAddress());
			ps.setFloat(5,shippingunit.getDelivery_fee());
			ps.setTimestamp(6, shippingunit.getCreatedAt());
			ps.setTimestamp(7, shippingunit.getUpdatedAt());

			
			int i = ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete(long id) {
		String sql = "DELETE shippingcompanys where id= ?";
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
