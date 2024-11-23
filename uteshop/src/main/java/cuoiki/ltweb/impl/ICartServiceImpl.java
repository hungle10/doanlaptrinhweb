package cuoiki.ltweb.impl;

import java.util.List;

import cuoiki.ltweb.models.CartModel;
import cuoiki.ltweb.services.ICartService;
import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.impl.*;

public class ICartServiceImpl implements ICartService{
	ICartDAO cartdao = new CartDAOImpl();

	@Override
	public int getCartCountByUserId(long uid) {
		return cartdao.getCartCountByUserId(uid);
	}

	@Override
	public List<CartModel> getCartListByUserId(long uid) {
		return cartdao.getCartListByUserId(uid);
	}
	@Override
	public boolean addToCart(CartModel cart)
	{
		return cartdao.addToCart(cart);
	}
	@Override
	public int getQuantity(long uid, long pid)
	{
		return cartdao.getQuantity(uid, pid);
	}
	@Override
	public int getIdCartByUserIdAndProductId(long uid, long pid) {
		return cartdao.getIdCartByUserIdAndProductId(uid, pid);
	}
	@Override
	public void updateQuantity(int id, int qty) {
		cartdao.updateQuantity(id, qty);
	}
	@Override
	public int getQuantityByCartId(int id) {
		return cartdao.getQuantityByCartId(id);
	}
	@Override
	public long getProductId(int cid) {
		return cartdao.getProductId(cid);
	}
	@Override
	public void removeProductInCart(int cid) {
		cartdao.removeProductInCart(cid);
	}
	@Override
	public void removeAllProductInCartByUserId(long uid) {
		cartdao.removeAllProductInCartByUserId(uid);
	}

}
