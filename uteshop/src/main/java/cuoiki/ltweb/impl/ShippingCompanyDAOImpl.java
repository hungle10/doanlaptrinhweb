package cuoiki.ltweb.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.*;

public class ShippingCompanyDAOImpl extends DBConnectSQLServer implements IShippingCompanyDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public int getIdShippingCompanyByName(String companyname) {
		int cid = 0;
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
	
	

}
