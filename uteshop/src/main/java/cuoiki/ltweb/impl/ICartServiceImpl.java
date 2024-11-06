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

}
