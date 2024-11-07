package cuoiki.ltweb.models;

import java.sql.Timestamp;

public class ShopModel {
  private long id;
  private long vendor_id;
  private String name;
  private String description;
  private String logo;
  private String address;
  private String phone_number;
  private String email;
  private Boolean is_active;
  private Timestamp created_at;
  private Timestamp updated_at;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getVendor_id() {
	return vendor_id;
}
public void setVendor_id(long vendor_id) {
	this.vendor_id = vendor_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getLogo() {
	return logo;
}
public void setLogo(String logo) {
	this.logo = logo;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Boolean getIs_active() {
	return is_active;
}
public void setIs_active(Boolean is_active) {
	this.is_active = is_active;
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
public ShopModel() {
	
}
public ShopModel(long id, long vendor_id, String name, String description, String logo, String address,
		String phone_number, String email, Boolean is_active, Timestamp created_at, Timestamp updated_at) {
	super();
	this.id = id;
	this.vendor_id = vendor_id;
	this.name = name;
	this.description = description;
	this.logo = logo;
	this.address = address;
	this.phone_number = phone_number;
	this.email = email;
	this.is_active = is_active;
	this.created_at = created_at;
	this.updated_at = updated_at;
}
public ShopModel(long vendor_id, String name, String description, String logo, String address, String phone_number,
		String email, Boolean is_active, Timestamp created_at, Timestamp updated_at) {
	super();
	this.vendor_id = vendor_id;
	this.name = name;
	this.description = description;
	this.logo = logo;
	this.address = address;
	this.phone_number = phone_number;
	this.email = email;
	this.is_active = is_active;
	this.created_at = created_at;
	this.updated_at = updated_at;
}
  
}
