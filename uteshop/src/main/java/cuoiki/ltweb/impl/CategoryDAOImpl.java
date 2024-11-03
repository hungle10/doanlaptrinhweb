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


}
