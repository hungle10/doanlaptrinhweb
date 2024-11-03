package cuoiki.ltweb.models;

public class WishlistModel {
	private Integer wishlistId;
	private Long userId;
	private Long productId;

	// Default constructor
	public WishlistModel() {
	    }

	// Parameterized constructor
	public WishlistModel(Integer wishlistId, Long userId, Long productId) {
	        this.wishlistId = wishlistId;
	        this.userId = userId;
	        this.productId = productId;
	    }

	// Getters and Setters
	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
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
}
