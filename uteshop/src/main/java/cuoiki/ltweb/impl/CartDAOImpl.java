package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.ICartDAO;
import cuoiki.ltweb.models.CartModel;

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
	
	@Override
	public List<CartModel> getCartListByUserId(long uid) {
		List<CartModel> list = new ArrayList<CartModel>();
		try {
			conn = super.getConnection();
			String query = "select * from cart where user_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, uid);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				CartModel cart = new CartModel();
				cart.setCartId(rs.getInt("cart_id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setProductId(rs.getInt("product_id"));
				cart.setQuantity(rs.getInt("quantity"));

				list.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
