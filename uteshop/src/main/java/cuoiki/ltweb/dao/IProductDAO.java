package cuoiki.ltweb.dao;

import java.util.List;
import cuoiki.ltweb.models.*;
public interface IProductDAO {
	public List<ProductModel> getAllLatestProducts();

	public List<ProductModel> getDiscountedProducts();

	public List<ProductModel> getAllProductsBySearchKey(String search,int currentpage);

	public List<ProductModel> getAllProductsByCategoryId(int catId,int currentpage);

	public List<ProductModel> getAllProducts();

	public List<ProductModel> getProductsBasedOnPageNumber(int currentpage);

	List<ProductModel> getAllProductsByCategoryId(int catId);

	List<ProductModel> getAllProductsBySearchKey(String search);
}
