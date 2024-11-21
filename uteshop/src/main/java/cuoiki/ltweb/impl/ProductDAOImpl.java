package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.IProductDAO;
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
	
	@Override
	public List<ProductModel> getDiscountedProducts() {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where discount >= 30 order by discount desc";
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
	//có phân trang
	@Override
	public List<ProductModel> getAllProductsBySearchKey(String search,int currentpage) {
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			conn = super.getConnection();
			String query = "select * from products where lower(name) like ? or lower(description) like ? ORDER BY id OFFSET (("+currentpage+"- 1) * 2)  ROWS FETCH NEXT 2 ROWS ONLY"; //tìm kiếm theo name và mô tả
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
				product.setShop_id(rs.getInt("shop_id"));
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
				product.setShop_id(rs.getInt("shop_id"));
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
			String query = "select * from products where category_id = ? ORDER BY id OFFSET (("+currentpage+"- 1) * 2)  ROWS FETCH NEXT 2 ROWS ONLY";
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
				product.setShop_id(rs.getInt("shop_id"));
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
				product.setShop_id(rs.getInt("shop_id"));
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
				product.setShop_id(rs.getInt("shop_id"));
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
			String query = "select * from products ORDER BY id OFFSET (("+currentpage+"- 1) * 2)  ROWS FETCH NEXT 2 ROWS ONLY";
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
				product.setShop_id(rs.getInt("shop_id"));
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
			product.setShop_id(rs.getInt("shop_id"));

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
			String query = "select s.* from shops s inner join products p on p.id = s.id where p.id = ?";
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
			shop.setUpdated_at(rs.getTimestamp(""));
			

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

}
