package cuoiki.ltweb.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cuoiki.ltweb.models.ShopModel;

public interface IShopService {

	boolean insertShop(ShopModel shop);

	List<ShopModel> getShopsByVendorId(long vendorid);

	ShopModel findById(long id);

	List<Map<String, Object>> getMonthlyRevenue(long shopId) throws SQLException, ClassNotFoundException;

	List<Map<String, Object>> getDailyRevenue(long shopId) throws SQLException, ClassNotFoundException;

	void update(ShopModel shop);

	List<ShopModel> findAll();

	void delete(long idshop);

}
