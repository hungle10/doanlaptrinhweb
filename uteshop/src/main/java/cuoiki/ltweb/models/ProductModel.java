package cuoiki.ltweb.models;

import java.sql.Timestamp;


public class ProductModel {
	private long id;
	private String name;
	private float price;
	private String description;
	private int quantity;
	private int discount;
	private String image;
	private long category_id;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private long shop_id;
	private int price_after_discount;
	private String category_name;

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public ProductModel(long id, String name, float price, String description, int quantity, int discount, String image,
			long category_id, Timestamp createdAt, Timestamp updatedAt, long shop_id, int price_after_discount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
		this.image = image;
		this.category_id = category_id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.shop_id = shop_id;
		this.price_after_discount = price_after_discount;
	}

	public int getPrice_after_discount() {
		return price_after_discount;
	}

	public void setPrice_after_discount(int price_after_discount) {
		this.price_after_discount = price_after_discount;
	}

	public ProductModel(String name, float price, String description, int quantity, int discount, String image,
			long category_id, Timestamp createdAt, Timestamp updatedAt, long shop_id) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
		this.image = image;
		this.category_id = category_id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.shop_id = shop_id;
	}

	public ProductModel(long id, String name, float price, String description, int quantity, int discount, String image,
			long category_id, Timestamp createdAt, Timestamp updatedAt, long shop_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
		this.image = image;
		this.category_id = category_id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.shop_id = shop_id;
	}

	// Default constructor
	public ProductModel() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
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

	public long getShop_id() {
		return shop_id;
	}

	public void setShop_id(long shop_id) {
		this.shop_id = shop_id;
	}

	
}
