package cuoiki.ltweb.impl;
import cuoiki.ltweb.models.ProductModel;
import cuoiki.ltweb.models.ShopModel;
import cuoiki.ltweb.services.*;

import java.util.List;

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
	@Override
	public ShopModel getShopByProductId(long product_id) {
		return productdao.getShopByProductId(product_id);
	}
	@Override
	public void updateQuantity(long id, int qty) {
		productdao.updateQuantity(id, qty);
	}
	@Override
	public int getProductQuantityById(long pid) {
		return productdao.getProductQuantityById(pid);
	}
	@Override
	public List<String> getProductsIdSoldOver10(){
		return productdao.getProductsIdSoldOver10();
	}
	@Override
	public List<ProductModel> getAllProductsByShopId(long shopId){
		return productdao.getAllProductsByShopId(shopId);
	}
	@Override
	public void insert(ProductModel product) {
		productdao.insert(product);
	}
	@Override
	public void update(ProductModel product) {
		productdao.update(product);
	}
	@Override
	public void delete(long idprod) {
		productdao.delete(idprod);
	}
	@Override
	public List<ProductModel> getAllProducts(){
		return productdao.getAllProducts();
	}
	@Override
	public List<ProductModel> getProductsBelongToPendingShop() {
		return productdao.getProductsBelongToPendingShop();
	}

}
