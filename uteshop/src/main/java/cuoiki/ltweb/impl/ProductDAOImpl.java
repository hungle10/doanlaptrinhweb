package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.IProductDAO;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;

public class ProductDAOImpl extends DBConnectSQLServer implements IProductDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<ProductModel> getAllLatestProducts() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "SELECT * FROM products ORDER BY id DESC";
			Statement statement = this.conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				list.add(
						new ProductModel(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getFloat("price"),
						rs.getString("description"),
						rs.getInt("quantity"),
						rs.getInt("discount"),
						rs.getString("image"),
						rs.getLong("category_id"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at"),
						rs.getLong("shop_id")
						)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
			return list;
	}
	//products bán trên 10 sản phẩm của các shop sắp xếp từ lớn đến nhỏ
	@Override
	public List<String> getProductsIdSoldOver10() {
		List<String> list = new ArrayList<String>();
		try {
			conn = super.getConnection();
			String query = "SELECT od.product_id, SUM(od.number_of_products) AS total_quantity\r\n"
					+ "FROM order_details od\r\n"
					+ "JOIN orders o ON od.order_id = o.id\r\n"
					+ "WHERE o.payment_status = 'paid'\r\n"
					+ "  AND od.status IN ('Delivered', 'CancelReturned')\r\n"
					+ "GROUP BY od.product_id\r\n"
					+ "HAVING SUM(od.number_of_products) > 10\r\n"
					+ "ORDER BY total_quantity DESC;";
			Statement statement = this.conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				 list.add(String.valueOf(rs.getLong("product_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//có phân trang
	@Override
	public List<ProductModel> getAllProductsBySearchKey(String search,int currentpage) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where lower(name) like ? or lower(description) like ? ORDER BY id OFFSET (("+currentpage+"- 1) * 20)  ROWS FETCH NEXT 20 ROWS ONLY"; //tìm kiếm theo name và mô tả
			PreparedStatement psmt = this.conn.prepareStatement(query);
			search = "%" + search + "%";
			search.toLowerCase();
			psmt.setString(1, search);
			psmt.setString(2, search);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<ProductModel> getProductsBelongToPendingShop() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "SELECT p.*\r\n"
					+ "FROM products p\r\n"
					+ "JOIN shops s ON p.shop_id = s.id\r\n"
					+ "WHERE s.is_active = 0;\r\n";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<ProductModel> getAllProductsBySearchKey(String search) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where lower(name) like ? or lower(description) like ?"; //tìm kiếm theo name và mô tả
			PreparedStatement psmt = this.conn.prepareStatement(query);
			search = "%" + search + "%";
			search.toLowerCase();
			psmt.setString(1, search);
			psmt.setString(2, search);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//có phân trang
	@Override
	public List<ProductModel> getAllProductsByCategoryId(int catId,int currentpage) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where category_id = ? ORDER BY id OFFSET (("+currentpage+"- 1) * 20)  ROWS FETCH NEXT 20 ROWS ONLY";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, catId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	@Override
	public List<ProductModel> getAllProductsByShopId(long shopId) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where shop_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, shopId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> getAllProductsByShopId(long shopId,int currentpage) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where shop_id = ? ORDER BY id OFFSET (("+currentpage+"- 1) * 20)  ROWS FETCH NEXT 20 ROWS ONLY";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, shopId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<ProductModel> getAllProductsByCategoryId(int catId) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where category_id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, catId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products";
			Statement statement = this.conn.createStatement();

			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//2 sp trong 1 trang
	@Override
	public List<ProductModel> getProductsBasedOnPageNumber(int currentpage) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products ORDER BY id OFFSET (("+currentpage+"- 1) * 20)  ROWS FETCH NEXT 20 ROWS ONLY";
			Statement statement = this.conn.createStatement();

			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDiscount(rs.getInt("discount"));
				product.setImage(rs.getString("image"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setCreatedAt(rs.getTimestamp("created_at"));
				product.setUpdatedAt(rs.getTimestamp("updated_at"));
				product.setShop_id(rs.getLong("shop_id"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public ProductModel getProductsByProductId(long product_id) {
		ProductModel product = new ProductModel();
		try {
			conn = super.getConnection();
			String query = "select * from products where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, product_id);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			product.setId(rs.getLong("id"));
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));
			product.setPrice(rs.getFloat("price"));
			product.setQuantity(rs.getInt("quantity"));
			product.setDiscount(rs.getInt("discount"));
			product.setImage(rs.getString("image"));
			product.setCategory_id(rs.getInt("category_id"));
			product.setCreatedAt(rs.getTimestamp("created_at"));
			product.setUpdatedAt(rs.getTimestamp("updated_at"));
			product.setShop_id(rs.getLong("shop_id"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	@Override
	public ShopModel getShopByProductId(long product_id) {
		ShopModel shop = new ShopModel();
		try {
			conn = super.getConnection();
			String query = "select s.* from shops s inner join products p on p.shop_id = s.id where p.id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, product_id);
			ResultSet rs = psmt.executeQuery();
			rs.next();
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
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return shop;
	}
	@Override
	public void updateQuantity(long id, int qty) {
		try {
			conn = super.getConnection();
			String query = "update products set quantity = ? where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, qty);
			psmt.setLong(2, id);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(ProductModel product) {
		try {
			conn = super.getConnection();
			String query = "update products set name = ?, price = ?, description = ?, quantity = ?, discount = ?, image= ?,category_id= ?,updated_at= ?,shop_id = ?  where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setString(1, product.getName());
			psmt.setFloat(2, product.getPrice());
			psmt.setString(3, product.getDescription());
			psmt.setInt(4, product.getQuantity());
			psmt.setInt(5, product.getDiscount());
			psmt.setString(6, product.getImage());
			psmt.setLong(7, product.getCategory_id());
			psmt.setTimestamp(8, product.getUpdatedAt());
			psmt.setLong(9, product.getShop_id());
			psmt.setLong(10, product.getId());

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int getProductQuantityById(long pid) {
		int qty = 0;
		try {
			conn = super.getConnection();
			String query = "select quantity from products where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1, pid);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			qty = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}
	

	@Override
	public void delete(long idprod) {
		String sql = "DELETE from products where id= ?";
		try {
			conn=super.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setLong(1,idprod);

			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		}catch(Exception e)
		{
			
		}
	}
	@Override
	public void insert(ProductModel product) {
		String sql = "INSERT INTO products (name,price,description,quantity,discount,image,category_id,created_at,updated_at,shop_id)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		try
		{
			conn=super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,product.getName());
			ps.setFloat(2, product.getPrice());
			ps.setString(3, product.getDescription());
			ps.setInt(4, product.getQuantity());
			ps.setInt(5, product.getDiscount());
			ps.setString(6,product.getImage());
			ps.setLong(7,product.getCategory_id());
			ps.setTimestamp(8, product.getCreatedAt());
			ps.setTimestamp(9,product.getUpdatedAt());
			ps.setLong(10,product.getShop_id());
			int i = ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	

	 

}
