package cuoiki.ltweb.models;

public class CartModel {
	private Integer cartId;
	private Long userId;
	private Long productId;
	private Integer quantity;

	// Default constructor
	public CartModel() {
    }

	// Parameterized constructor
	public CartModel(Integer cartId, Long userId, Long productId, Integer quantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

	// Getters and Setters
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
