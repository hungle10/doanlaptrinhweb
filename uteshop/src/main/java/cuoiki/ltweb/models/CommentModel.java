package cuoiki.ltweb.models;

import java.sql.Timestamp;

public class CommentModel {
	private long id;
	private long user_id;
	private long product_id;
	private String comment_text;
	private String image;
	private String video;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String username;
	private String image_user;
	
	
	
	public String getImage_user() {
		return image_user;
	}

	public void setImage_user(String image_user) {
		this.image_user = image_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CommentModel(long id, long user_id, long product_id, String comment_text, String image, String video,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.comment_text = comment_text;
		this.image = image;
		this.video = video;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public CommentModel(long user_id, long product_id, String comment_text, String image, String video,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.user_id = user_id;
		this.product_id = product_id;
		this.comment_text = comment_text;
		this.image = image;
		this.video = video;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public CommentModel(long id, long user_id, long product_id, String comment_text, String image, String video,
		 Timestamp updated_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.comment_text = comment_text;
		this.image = image;
		this.video = video;
		this.updated_at = updated_at;
	}

	public CommentModel() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	

}
