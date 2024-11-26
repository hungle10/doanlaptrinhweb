package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.WishlistModel;

public interface IWishlistService {

	List<WishlistModel> getListByUserId(long uid);

}
