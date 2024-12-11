package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.IShopDAO;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.models.UserModel;

public class ShopDAOImpl extends DBConnectSQLServer implements IShopDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<ShopModel> getShopsByVendorId(long vendorid) {
		List<ShopModel> list = new ArrayList<ShopModel>();
		String query = "SELECT * FROM shops where vendor_id = ?";
		try {
			conn = super.getConnection();
		    ps = conn.prepareStatement(query);
		    ps.setLong(1,vendorid); 
		    rs = ps.executeQuery(); 
			while (rs.next()) {
				list.add(
						new ShopModel(
						rs.getLong("id"),
						rs.getLong("vendor_id"),
						rs.getString("shop_name"),
						rs.getString("description"),
						rs.getString("logo"),
						rs.getString("address"),
						rs.getString("phone_number"),
						rs.getString("email"),
						rs.getBoolean("is_active"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at")
						)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
			return list;
	}
	
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
	@Override
	public List<ShopModel> findAll() {
		List<ShopModel> list = new ArrayList<ShopModel>();
		try {
			conn = super.getConnection();
			String query = "select * from shops";
			Statement statement = this.conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				ShopModel shop = new ShopModel();
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
				list.add(shop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
			return list;
	}
	
	

	@Override
	public boolean insertShop(ShopModel shop) {
		boolean isSuccess = true;
		try {
			conn = super.getConnection();
			String query = "insert into shops (vendor_id,shop_name,description,logo,address,phone_number,email,is_active,created_at,updated_at) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			
			psmt.setLong(1,shop.getVendor_id());
			psmt.setString(2,shop.getName());
			psmt.setString(3,shop.getDescription());
			psmt.setString(4,shop.getLogo());
			psmt.setString(5, shop.getAddress());
			psmt.setString(6, shop.getPhone_number());
			psmt.setString(7,shop.getEmail());
			psmt.setBoolean(8, shop.getIs_active());
			psmt.setTimestamp(9,shop.getCreated_at());
			psmt.setTimestamp(10, shop.getUpdated_at());
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
	public List<Map<String, Object>> getMonthlyRevenue(long shopId) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> revenueData = new ArrayList<>();

        String sql = "SELECT YEAR(o.created_at) AS year, MONTH(o.created_at) AS month, SUM(od.total_money) AS monthly_revenue " +
                     "FROM order_details od " +
                     "JOIN products p ON od.product_id = p.id " +
                     "JOIN orders o ON od.order_id = o.id " +
                     "WHERE p.shop_id = ? " +
                     "GROUP BY YEAR(o.created_at), MONTH(o.created_at) " +
                     "ORDER BY year, month;";

        try {
        	 conn = super.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, shopId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("year", rs.getInt("year"));
                record.put("month", rs.getInt("month"));
                record.put("revenue", rs.getDouble("monthly_revenue"));
                revenueData.add(record);
            }
        }catch (Exception e) {
			e.printStackTrace();
		}

        return revenueData;
    }

	@Override
	public List<Map<String, Object>> getDailyRevenue(long shopId) throws SQLException, ClassNotFoundException {
	    List<Map<String, Object>> revenueData = new ArrayList<>();

	    // SQL truy vấn tính doanh thu theo ngày
	    String sql = "SELECT CAST(o.created_at AS DATE) AS day, SUM(od.total_money) AS daily_revenue " +
	                 "FROM order_details od " +
	                 "JOIN orders o ON od.order_id = o.id " +
	                 "JOIN products p ON od.product_id = p.id " +
	                 "WHERE p.shop_id = ? " +
	                 "AND o.payment_status = 'paid'"+
	                 "AND od.status IN ('Delivered', 'CancelReturned')"+
	                 "GROUP BY CAST(o.created_at AS DATE) " +
	                 "ORDER BY day;";

	    try {
	        // Kết nối với cơ sở dữ liệu
	        conn = super.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        // Thiết lập tham số shopId vào câu truy vấn
	        stmt.setLong(1, shopId);
	        
	        // Thực thi truy vấn
	        ResultSet rs = stmt.executeQuery();

	        // Xử lý kết quả trả về
	        while (rs.next()) {
	            Map<String, Object> record = new HashMap<>();
	            record.put("day", rs.getDate("day"));  // Lấy ngày
	            record.put("revenue", rs.getDouble("daily_revenue"));  // Lấy doanh thu hàng ngày
	            revenueData.add(record);  // Thêm kết quả vào danh sách
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Đảm bảo đóng kết nối nếu cần thiết
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return revenueData;  // Trả về dữ liệu doanh thu theo ngày
	}
	
	
	@Override
	public void update(ShopModel shop) {
		 String sql = "UPDATE shops SET vendor_id = ? , shop_name = ? , description = ? , logo = ? ,address = ? , phone_number = ?, email = ?,is_active = ?,created_at = ?,updated_at = ? WHERE id = ?";
		    try {
		        conn = super.getConnection();
		        ps = conn.prepareStatement(sql);
		        ps.setLong(1,shop.getVendor_id());
		        ps.setString(2,shop.getName());
		        ps.setString(3,shop.getDescription());
		        ps.setString(4,shop.getLogo());
		        ps.setString(5,shop.getAddress());
		        ps.setString(6,shop.getPhone_number());
		        ps.setString(7,shop.getEmail());
		        ps.setBoolean(8,shop.getIs_active());
		        ps.setTimestamp(9,shop.getCreated_at());
		        ps.setTimestamp(10,shop.getUpdated_at());
		        ps.setLong(11,shop.getId());
		        ps.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
	}
	@Override
	public void delete(long idshop) {
		String sql = "DELETE shops where id= ?";
		try {
			conn=super.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setLong(1,idshop);
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		}catch(Exception e)
		{
			
		}
	}


}
