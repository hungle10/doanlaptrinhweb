package cuoiki.ltweb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.CartModel;
import cuoiki.ltweb.models.ChatModel;

public class ChatDAOImpl extends DBConnectSQLServer implements IChatDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	
	
	@Override
	public List<ChatModel> getChatListBySenderAndReceiver(long senderid,long receiverid) {
		List<ChatModel> list = new ArrayList<ChatModel>();
		try {
			conn = super.getConnection();
			String query = "select * from chats where id_user_from = ? and id_user_to = ?";
			PreparedStatement psmt = this.conn.prepareStatement(query);
			psmt.setLong(1,senderid);
			psmt.setLong(2,receiverid);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ChatModel chat = new ChatModel();
				chat.setChatId(rs.getLong("id"));
				chat.setId_user_from(rs.getLong("id_user_from"));
				chat.setId_user_to(rs.getLong("id_user_to"));
				chat.setMessage_content(rs.getString("message_content"));
				chat.setCreated_at(rs.getTimestamp("created_at"));
				

				list.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ChatModel> getChatList() {
		List<ChatModel> list = new ArrayList<ChatModel>();
		try {
			conn = super.getConnection();
			String query = "select * from chats";
			PreparedStatement psmt = this.conn.prepareStatement(query);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ChatModel chat = new ChatModel();
				chat.setChatId(rs.getLong("id"));
				chat.setId_user_from(rs.getLong("id_user_from"));
				chat.setId_user_to(rs.getLong("id_user_to"));
				chat.setMessage_content(rs.getString("message_content"));
				chat.setCreated_at(rs.getTimestamp("created_at"));
				

				list.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean addToChatInDB(ChatModel chat) {
		boolean flag = false;
		try {
			conn = super.getConnection();
			String query = "insert into chats(id_user_from,id_user_to,message_content,created_at) values(?,?,?,?)";
			PreparedStatement psmt = this.conn.prepareStatement(query);
		
			psmt.setLong(1, chat.getId_user_from());
			psmt.setLong(2, chat.getId_user_to());
			psmt.setString(3, chat.getMessage_content());
			psmt.setTimestamp(4,chat.getCreated_at());
			psmt.executeUpdate();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
