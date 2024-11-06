package cuoiki.ltweb.models;

public class WishlistModel {
	private int wishlistId;
	private long userId;
	private long productId;

	// Default constructor
	public WishlistModel() {
	    }

	// Parameterized constructor
	public WishlistModel(int wishlistId, long userId, long productId) {
	        this.wishlistId = wishlistId;
	        this.userId = userId;
	        this.productId = productId;
	    }

	// Getters and Setters
	public int getWishlistId() {
		return wishlistId;
	}

	public WishlistModel(long userId, long productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
}
