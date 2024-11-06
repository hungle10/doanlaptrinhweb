package cuoiki.ltweb.dao;

import cuoiki.ltweb.models.WishlistModel;

public interface IWishlistDAO {

	public boolean getWishlist(long uid, long pid);

	public void deleteWishlist(long uid,long pid);

	public boolean addToWishlist(WishlistModel w);

}
