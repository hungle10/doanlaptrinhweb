package cuoiki.ltweb.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CategoryModel {
	private Long id;
	private String name;
	private String image;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Default constructor
	public CategoryModel() {
	}

	public CategoryModel(Long id, String name, String image, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


}
