package cuoiki.ltweb.impl;

import cuoiki.ltweb.services.IWishlistService;

import java.util.List;

import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.WishlistModel;

public class IWishlistServiceImpl implements IWishlistService{
	IWishlistDAO wishlist_dao = new WishlistDAOImpl();
	
	@Override
	public List<WishlistModel> getListByUserId(long uid){
		return wishlist_dao.getListByUserId(uid);
	}
	

}
