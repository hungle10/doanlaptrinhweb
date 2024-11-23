package cuoiki.ltweb.models;

import java.sql.Date;
import java.sql.*;

public class OrderModel {
	private long id;
	private long userid;
	private long shippingcompanyid;
	private Date orderdate;
	private String status;
	private float totalmoney;
    private String payment_method;
    private String payment_status;
    private String shipping_address;
    private Timestamp createdAt;
	private Timestamp updatedAt;
	public OrderModel() {
		
	}	
	public OrderModel(long id, long userid, long shippingcompanyid, Date orderdate, String status, float totalmoney,
			String payment_method, String payment_status, String shipping_address, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.id = id;
		this.userid = userid;
		this.shippingcompanyid = shippingcompanyid;
		this.orderdate = orderdate;
		this.status = status;
		this.totalmoney = totalmoney;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.shipping_address = shipping_address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public OrderModel(long userid, long shippingcompanyid, Date orderdate, String status, float totalmoney,
			String payment_method, String payment_status, String shipping_address, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.userid = userid;
		this.shippingcompanyid = shippingcompanyid;
		this.orderdate = orderdate;
		this.status = status;
		this.totalmoney = totalmoney;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.shipping_address = shipping_address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getShippingcompanyid() {
		return shippingcompanyid;
	}
	public void setShippingcompanyid(long shippingcompanyid) {
		this.shippingcompanyid = shippingcompanyid;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(float totalmoney) {
		this.totalmoney = totalmoney;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
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
