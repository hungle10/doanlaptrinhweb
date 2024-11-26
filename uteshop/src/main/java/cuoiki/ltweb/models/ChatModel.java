package cuoiki.ltweb.models;

import java.sql.Timestamp;

public class ChatModel {
	private long chatId;
	private long id_user_from; //người gửi
	private long id_user_to;  // người nhận
	private String message_content;
	private Timestamp created_at;
	
	public ChatModel() {
		
	}
	
	public ChatModel(long chatId, long id_user_from, long id_user_to, String message_content, Timestamp created_at) {
		super();
		this.chatId = chatId;
		this.id_user_from = id_user_from;
		this.id_user_to = id_user_to;
		this.message_content = message_content;
		this.created_at = created_at;
	}
	
	public ChatModel(long id_user_from, long id_user_to, String message_content, Timestamp created_at) {
		super();
		this.id_user_from = id_user_from;
		this.id_user_to = id_user_to;
		this.message_content = message_content;
		this.created_at = created_at;
	}

	public long getChatId() {
		return chatId;
	}
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	public long getId_user_from() {
		return id_user_from;
	}
	public void setId_user_from(long id_user_from) {
		this.id_user_from = id_user_from;
	}
	public long getId_user_to() {
		return id_user_to;
	}
	public void setId_user_to(long id_user_to) {
		this.id_user_to = id_user_to;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	

}
