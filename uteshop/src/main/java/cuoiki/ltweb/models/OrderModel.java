package cuoiki.ltweb.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderModel {
	private Long id;
	private Long userId;
	private String fullname;
	private String email;
	private String phoneNumber;
	private String address;
	private String note;
	private LocalDate orderDate;
	private String status;
	private Float totalMoney;
	private String shippingMethod;
	private String shippingAddress;
	private LocalDate shippingDate;
	private String trackingNumber;
	private String paymentMethod;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// Default constructor
	public OrderModel() {
	    }

	// Parameterized constructor
	public OrderModel(Long id, Long userId, String fullname, String email, String phoneNumber, String address, String note,
	                 LocalDate orderDate, String status, Float totalMoney, String shippingMethod, String shippingAddress,
	                 LocalDate shippingDate, String trackingNumber, String paymentMethod, Boolean active,
	                 LocalDateTime createdAt, LocalDateTime updatedAt) {
	        this.id = id;
	        this.userId = userId;
	        this.fullname = fullname;
	        this.email = email;
	        this.phoneNumber = phoneNumber;
	        this.address = address;
	        this.note = note;
	        this.orderDate = orderDate;
	        this.status = status;
	        this.totalMoney = totalMoney;
	        this.shippingMethod = shippingMethod;
	        this.shippingAddress = shippingAddress;
	        this.shippingDate = shippingDate;
	        this.trackingNumber = trackingNumber;
	        this.paymentMethod = paymentMethod;
	        this.active = active;
	        this.createdAt = createdAt;
	        this.updatedAt = updatedAt;
	    }

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
