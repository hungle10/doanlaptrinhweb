package cuoiki.ltweb.models;

public class OrderDetailModel {
	private long id;
	private long orderId;
	private long productId;
	private float price;
	private int numberOfProducts;
	private float totalMoney;
	private ProductModel product;
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public OrderDetailModel(long id, long orderId, long productId, float price, int numberOfProducts,
			float totalMoney) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.price = price;
		this.numberOfProducts = numberOfProducts;
		this.totalMoney = totalMoney;
	}
	
	public OrderDetailModel(long orderId, long productId, float price, int numberOfProducts, float totalMoney) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.price = price;
		this.numberOfProducts = numberOfProducts;
		this.totalMoney = totalMoney;
	}

	public OrderDetailModel() {
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumberOfProducts() {
		return numberOfProducts;
	}
	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}
	public float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	
}
