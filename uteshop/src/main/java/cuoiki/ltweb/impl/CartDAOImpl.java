package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.ICartDAO;

public class CartDAOImpl extends DBConnectSQLServer implements ICartDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public int getCartCountByUserId(long uid) {
		int count = 0;
		try {
			conn = super.getConnection();
			String query = "select count(*) from cart where user_id=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setLong(1, uid);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
