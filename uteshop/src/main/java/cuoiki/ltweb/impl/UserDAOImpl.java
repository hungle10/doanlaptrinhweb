package cuoiki.ltweb.impl;

import cuoiki.ltweb.configs.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.dao.IUserDAO;
import cuoiki.ltweb.models.UserModel;


public class UserDAOImpl extends DBConnectSQLServer implements IUserDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * from users";
		List<UserModel> list = new ArrayList<>();
		try
		{
			conn = super.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				list.add(
						new UserModel(
								rs.getLong("id"),
								rs.getString("username"),
								rs.getString("fullname"),
			                    rs.getString("phone_number"),
			                    rs.getString("address"),
			                    rs.getString("email"),
			                    rs.getString("password"),
			                    rs.getBoolean("is_active"),
			                    rs.getDate("date_of_birth"),
			                    rs.getString("image"),
			                    rs.getLong("facebook_account_id"),
			                    rs.getLong("google_account_id"),
			                    rs.getInt("role_id"),
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
	public UserModel findById(long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		UserModel oneUser = new UserModel();

		try {
		    conn = super.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setLong(1,id); 
		    rs = ps.executeQuery(); 
		    while(rs.next())
			{
		    	oneUser.setId(rs.getLong("id"));
			    oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setPhoneNumber(rs.getString("phone_number"));
				oneUser.setAddress(rs.getString("address"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setIsActive(rs.getBoolean("is_active"));
				oneUser.setDateOfBirth(rs.getDate("date_of_birth"));
				oneUser.setImage(rs.getString("image"));
				oneUser.setFacebookAccountId(rs.getLong("facebook_account_id"));
				oneUser.setGoogleAccountId(rs.getLong("google_account_id"));
				oneUser.setRoleId(rs.getInt("role_id"));
				oneUser.setUpdatedAt(rs.getTimestamp("updated_at"));
				oneUser.setCreatedAt(rs.getTimestamp("created_at"));
			}
		    return oneUser;
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
	public UserModel findByFbId(long id) {
		String sql = "SELECT * FROM users WHERE facebook_account_id = ?";
		UserModel oneUser = new UserModel();

		try {
		    conn = super.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setLong(1,id); 
		    rs = ps.executeQuery(); 
		    while(rs.next())
			{
		    	oneUser.setId(rs.getLong("id"));
			    oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setPhoneNumber(rs.getString("phone_number"));
				oneUser.setAddress(rs.getString("address"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setIsActive(rs.getBoolean("is_active"));
				oneUser.setDateOfBirth(rs.getDate("date_of_birth"));
				oneUser.setImage(rs.getString("image"));
				oneUser.setFacebookAccountId(rs.getLong("facebook_account_id"));
				oneUser.setGoogleAccountId(rs.getLong("google_account_id"));
				oneUser.setRoleId(rs.getInt("role_id"));
				oneUser.setUpdatedAt(rs.getTimestamp("updated_at"));
				oneUser.setCreatedAt(rs.getTimestamp("created_at"));
			}
		    return oneUser;
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
	public void insert(UserModel user) {
		String sql = "INSERT INTO users (username , fullname, phone_number, address,email,password, is_active, date_of_birth, image,facebook_account_id,google_account_id,role_id, created_at, updated_at)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			conn=super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getFullname());
			ps.setString(3,user.getPhoneNumber());
			ps.setString(4,user.getAddress());
			ps.setString(5,user.getEmail());
			ps.setString(6,user.getPassword());
			ps.setBoolean(7,user.getIsActive());
			ps.setDate(8,user.getDateOfBirth());
			ps.setString(9,user.getImage());
			ps.setLong(10,user.getFacebookAccountId());
			ps.setLong(11,user.getGoogleAccountId());
			ps.setInt(12,user.getRoleId());
			ps.setTimestamp(13,user.getCreatedAt());
			ps.setTimestamp(14,user.getUpdatedAt());
			int i = ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
	    UserModel oneUser = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = super.getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        rs = ps.executeQuery();

	        while(rs.next()) {
	        	oneUser = new UserModel();
	        	oneUser.setId(rs.getLong("id"));
			    oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setPhoneNumber(rs.getString("phone_number"));
				oneUser.setAddress(rs.getString("address"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setIsActive(rs.getBoolean("is_active"));
				oneUser.setDateOfBirth(rs.getDate("date_of_birth"));
				oneUser.setImage(rs.getString("image"));
				oneUser.setFacebookAccountId(rs.getLong("facebook_account_id"));
				oneUser.setGoogleAccountId(rs.getLong("google_account_id"));
				oneUser.setRoleId(rs.getInt("role_id"));
				oneUser.setUpdatedAt(rs.getTimestamp("updated_at"));
				oneUser.setCreatedAt(rs.getTimestamp("created_at"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in reverse order of creation
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return oneUser;
	    
	}

	@Override
	public UserModel findByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		UserModel oneUser = new UserModel();

		try {
		    conn = super.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.setString(1,email); 
		    rs = ps.executeQuery(); 
		    while(rs.next())
			{
		    	oneUser.setId(rs.getLong("id"));
			    oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setPhoneNumber(rs.getString("phone_number"));
				oneUser.setAddress(rs.getString("address"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("password"));
				oneUser.setIsActive(rs.getBoolean("is_active"));
				oneUser.setDateOfBirth(rs.getDate("date_of_birth"));
				oneUser.setImage(rs.getString("image"));
				oneUser.setFacebookAccountId(rs.getLong("facebook_account_id"));
				oneUser.setGoogleAccountId(rs.getLong("google_account_id"));
				oneUser.setRoleId(rs.getInt("role_id"));
				oneUser.setUpdatedAt(rs.getTimestamp("updated_at"));
				oneUser.setCreatedAt(rs.getTimestamp("created_at"));
			}
		    return oneUser;
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
	public boolean checkExistEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		boolean duplicate = false;
		try 
		{
		    conn = super.getConnection();
		    ps=conn.prepareStatement(sql);
		    ps.setString(1,email);
		    rs=ps.executeQuery();
		    if(rs.next()) {
		    	duplicate=true;
		    }
		    ps.close();
		    conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		boolean duplicate = false;
		try 
		{
		    conn = super.getConnection();
		    ps=conn.prepareStatement(sql);
		    ps.setString(1,username);
		    rs=ps.executeQuery();
		    if(rs.next()) {
		    	duplicate=true;
		    }
		    ps.close();
		    conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return duplicate;
	}
	@Override
	public boolean checkExistFbId(long facebook_account_id) {
		String sql = "SELECT * FROM users WHERE facebook_account_id = ?";
		boolean duplicate = false;
		try 
		{
		    conn = super.getConnection();
		    ps=conn.prepareStatement(sql);
		    ps.setLong(1,facebook_account_id);
		    rs=ps.executeQuery();
		    if(rs.next()) {
		    	duplicate=true;
		    }
		    ps.close();
		    conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public void update(UserModel user) {
		 String sql = "UPDATE users SET fullname = ? , phone_number = ? , address = ? , email = ? , date_of_birth = ? , image = ?, updated_at = ? WHERE id = ?";
		    try {
		        conn = super.getConnection();
		        ps = conn.prepareStatement(sql);
		        ps.setString(1, user.getFullname());
		        ps.setString(2, user.getPhoneNumber());
		        ps.setString(3, user.getEmail());
		        ps.setDate(4,user.getDateOfBirth());
		        ps.setString(5,user.getImage());
		        ps.setTimestamp(6,user.getUpdatedAt());
		        ps.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
	}


}
