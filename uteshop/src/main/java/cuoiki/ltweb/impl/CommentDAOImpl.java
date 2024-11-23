package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.ICommentDAO;
import cuoiki.ltweb.models.CommentModel;
import cuoiki.ltweb.models.UserModel;

public class CommentDAOImpl extends DBConnectSQLServer implements ICommentDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	

	@Override
	public List<CommentModel> getAllComments() {
		List<CommentModel> list = new ArrayList<CommentModel>();
		try {
			conn = super.getConnection();
			String query = "select * from comments";
			Statement statement = this.conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				list.add(
						new CommentModel(
						rs.getLong("id"),
						rs.getLong("user_id"),
						rs.getLong("product_id"),
						rs.getString("comment_text"),
						rs.getString("image"),
						rs.getString("video"),
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
	public void insert(CommentModel comment) {
		String sql = "INSERT INTO comments (user_id, product_id, comment_text,image,video,created_at, updated_at)\r\n"
				+ "VALUES (?,?,?,?,?,?,?)";
		try
		{
			conn=super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1,comment.getUser_id());
			ps.setLong(2,comment.getProduct_id());
			ps.setString(3,comment.getComment_text());
			ps.setString(4,comment.getImage());
			ps.setString(5,comment.getVideo());
			ps.setTimestamp(6,comment.getCreated_at());
			ps.setTimestamp(7,comment.getUpdated_at());
			int i = ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
