package cuoiki.ltweb.dao;

import java.util.List;
import cuoiki.ltweb.models.*;
public interface IProductDAO {
	public List<ProductModel> getAllLatestProducts();
	
	public List<ProductModel> getAllProductsBySearchKey(String search,int currentpage);

	public List<ProductModel> getAllProductsByCategoryId(int catId,int currentpage);

	public List<ProductModel> getAllProducts();

	public List<ProductModel> getProductsBasedOnPageNumber(int currentpage);

	public List<ProductModel> getAllProductsByCategoryId(int catId);

	public List<ProductModel> getAllProductsBySearchKey(String search);

	public ProductModel getProductsByProductId(long product_id);

	public ShopModel getShopByProductId(long product_id);

	public void updateQuantity(long id, int qty);

	public int getProductQuantityById(long pid);

	public List<String> getProductsIdSoldOver10();

	List<ProductModel> getAllProductsByShopId(long shopId, int currentpage);

	List<ProductModel> getAllProductsByShopId(long shopId);

	void insert(ProductModel product);

	void update(ProductModel product);

	void delete(long idprod);

	List<ProductModel> getProductsBelongToPendingShop();

}
