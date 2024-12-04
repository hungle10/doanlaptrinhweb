package cuoiki.ltweb.models;

import java.sql.Timestamp;


public class ShippingCompanyModel {
	private long id;
	private String name;
	private String contact_number;
	private String email;
	private String address;
	private float delivery_fee;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public ShippingCompanyModel() {
		
	}
	
	public ShippingCompanyModel(long id, String name, String contact_number, String email, String address,
			float delivery_fee, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.contact_number = contact_number;
		this.email = email;
		this.address = address;
		this.delivery_fee = delivery_fee;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public ShippingCompanyModel(long id, String name, String contact_number, String email, String address,
			float delivery_fee,  Timestamp updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.contact_number = contact_number;
		this.email = email;
		this.address = address;
		this.delivery_fee = delivery_fee;

		this.updatedAt = updatedAt;
	}
	
	public ShippingCompanyModel(String name, String contact_number, String email, String address, float delivery_fee,
			Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.name = name;
		this.contact_number = contact_number;
		this.email = email;
		this.address = address;
		this.delivery_fee = delivery_fee;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getDelivery_fee() {
		return delivery_fee;
	}
	public void setDelivery_fee(float delivery_fee) {
		this.delivery_fee = delivery_fee;
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
