package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.CartModel;

public interface ICartDAO {

	public int getCartCountByUserId(long uid);

	public List<CartModel> getCartListByUserId(long uid);

	public boolean addToCart(CartModel cart);

	public int getQuantity(long uid, long pid);

	void updateQuantity(int id, int qty);

	public int getIdCartByUserIdAndProductId(long uid, long pid);

	public int getQuantityByCartId(int id);

	public long getProductId(int cid);

	public void removeProductInCart(int cid);

	void removeAllProductInCartByUserId(long uid);

}
