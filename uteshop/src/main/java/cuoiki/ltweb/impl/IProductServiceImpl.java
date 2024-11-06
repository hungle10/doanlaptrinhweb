package cuoiki.ltweb.impl;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.dao.*;
public class IProductServiceImpl implements IProductService{
	IProductDAO productdao = new ProductDAOImpl();

	@Override
	public int getProductPriceAfterDiscount(int productdiscount,float productprice) {
		int discount = (int) (( productdiscount/100.0) * productprice);
        return (int) (productprice - discount);
	}

	@Override
	public ProductModel getProductsByProductId(long product_id) {
		return productdao.getProductsByProductId(product_id);
	}

}
