package cuoiki.ltweb.services;

import cuoiki.ltweb.models.ProductModel;

public interface IProductService {

	public int getProductPriceAfterDiscount(int productdiscount, float productprice);
	//calculate price of product by applying discount
	public ProductModel getProductsByProductId(long product_id);
    
}
