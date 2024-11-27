package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.IShopDAO;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;

public class ShopDAOImpl extends DBConnectSQLServer implements IShopDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public ShopModel findById(long id) {
		String sql = "SELECT * FROM shops WHERE id = ?";
		ShopModel shop = new ShopModel();

		try {
		    conn = super.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setLong(1,id); 
		    rs = ps.executeQuery(); 
		    while(rs.next())
			{
		    	shop.setId(rs.getLong("id"));
		    	shop.setVendor_id(rs.getLong("vendor_id"));
		    	shop.setName(rs.getString("shop_name"));
		    	shop.setDescription(rs.getString("description"));
		    	shop.setLogo(rs.getString("logo"));
		    	shop.setAddress(rs.getString("address"));
		    	shop.setPhone_number(rs.getString("phone_number"));
		    	shop.setEmail(rs.getString("email"));
		    	shop.setIs_active(rs.getBoolean("is_active"));
		    	shop.setCreated_at(rs.getTimestamp("created_at"));
		    	shop.setUpdated_at(rs.getTimestamp("updated_at"));
			}
		    return shop;
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
