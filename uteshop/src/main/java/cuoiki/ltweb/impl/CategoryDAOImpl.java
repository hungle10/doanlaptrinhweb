package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.ICategoryDAO;
import cuoiki.ltweb.models.CategoryModel;


public class CategoryDAOImpl extends DBConnectSQLServer implements ICategoryDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from categories";
		List<CategoryModel> list = new ArrayList<>();
		try
		{
			conn = super.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				list.add(
						new CategoryModel(
								rs.getLong("id"),
								rs.getString("name"),
								rs.getString("image"),
			                    rs.getTimestamp("created_at"),
			                    rs.getTimestamp("updated_at")
						)
						);
			}
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String getCategoryName(int catId) {
		String category = "";
		try {
			conn = super.getConnection();
			String query = "select * from categories where id = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setInt(1, catId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				category = rs.getString("name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}


}
