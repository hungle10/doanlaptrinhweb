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
	@Override
	public boolean addToCart(CartModel cart) {
		boolean flag = false;
		try {
			conn = super.getConnection();
			String query = "insert into cart(user_id,product_id,quantity) values(?,?,?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, cart.getUserId());
			psmt.setLong(2, cart.getProductId());
			psmt.setInt(3, cart.getQuantity());

			psmt.executeUpdate();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public int getQuantity(long uid, long pid) {
		int qty = 0;
		try {
			conn = super.getConnection();
			String query = "select * from cart where user_id = ? and product_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, uid);
			psmt.setLong(2, pid);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				qty = rs.getInt("quantity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}
	@Override
	public int getIdCartByUserIdAndProductId(long uid, long pid) {
		int cid = 0;
		try {
			conn = super.getConnection();
			String query = "select * from cart where user_id = ? and product_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, uid);
			psmt.setLong(2, pid);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				cid = rs.getInt("cart_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cid;
	}
	@Override
	public void updateQuantity(int id, int qty) {

		try {
			conn = super.getConnection();
			String query = "update cart set quantity = ? where cart_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, qty);
			psmt.setInt(2, id);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int getQuantityByCartId(int id) {
		int qty = 0;
		try {
			conn = super.getConnection();
			String query = "select * from cart where cart_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, id);	
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				qty = rs.getInt("quantity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}
	@Override
	public long getProductId(int cid) {
		long pid = 0;
		try {
			conn = super.getConnection();
			String query = "select product_id from cart where cart_id=?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, cid);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			pid = rs.getLong(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pid;
	}
	
	@Override
	public void removeProductInCart(int cid) {
		try {
			conn = super.getConnection();
			String query = "delete from cart where cart_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, cid);

			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void removeAllProductInCartByUserId(long uid) {
		try {
			conn = super.getConnection();
			String query = "delete from cart where user_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1,uid);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
