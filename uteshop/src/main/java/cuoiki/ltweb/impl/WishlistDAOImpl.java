package cuoiki.ltweb.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.WishlistModel;
public class WishlistDAOImpl extends DBConnectSQLServer implements IWishlistDAO{
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	
	@Override
	public boolean getWishlist(long uid,long pid) {
		boolean flag = false;
		try {
			conn = super.getConnection();
			String query = "select * from wishlist where user_id = ? and product_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, uid);
			psmt.setLong(2, pid);
			
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean addToWishlist(WishlistModel w) {
		boolean flag = false;
		try {
			conn = super.getConnection();
			String query = "insert into wishlist(user_id,product_id) values(?,?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, w.getUserId());
			psmt.setLong(2, w.getProductId());
			
			psmt.executeUpdate();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public void deleteWishlist(long uid, long pid) {
		try {
			conn = super.getConnection();
			String query = "delete from wishlist where user_id = ? and product_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, uid);
			psmt.setLong(2, pid);
			
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
