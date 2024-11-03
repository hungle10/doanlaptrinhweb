package cuoiki.ltweb.models;

public class OrderDetailModel {
	private Long id;
	private Long orderId;
	private Long productId;
	private Float price;
	private Integer numberOfProducts;
	private Float totalMoney;
	private String color;

	// Default constructor
	public OrderDetailModel() {
	    }

	// Parameterized constructor
	public OrderDetailModel(Long id, Long orderId, Long productId, Float price, Integer numberOfProducts, 
	                       Float totalMoney, String color) {
	        this.id = id;
	        this.orderId = orderId;
	        this.productId = productId;
	        this.price = price;
	        this.numberOfProducts = numberOfProducts;
	        this.totalMoney = totalMoney;
	        this.color = color;
	    }

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
