package cuoiki.ltweb.services;

import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;

public interface IProductService {

	public int getProductPriceAfterDiscount(int productdiscount, float productprice);
	//calculate price of product by applying discount
	public ProductModel getProductsByProductId(long product_id);
	public ShopModel getShopByProductId(long product_id);
    
}
