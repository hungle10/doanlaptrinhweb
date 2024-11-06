package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.CartModel;

public interface ICartDAO {

	public int getCartCountByUserId(long uid);

	public List<CartModel> getCartListByUserId(long uid);

}
