package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.CartModel;

public interface ICartService {

	public List<CartModel> getCartListByUserId(long uid);

	public int getCartCountByUserId(long uid);


}
