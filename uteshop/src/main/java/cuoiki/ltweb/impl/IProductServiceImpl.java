package cuoiki.ltweb.impl;
import cuoiki.ltweb.services.*;
public class IProductServiceImpl implements IProductService{

	@Override
	public int getProductPriceAfterDiscount(int productdiscount,float productprice) {
		int discount = (int) (( productdiscount/100.0) * productprice);
        return (int) (productprice - discount);
	}

}
