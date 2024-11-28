package cuoiki.ltweb.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.*;
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
	
	
	

}
