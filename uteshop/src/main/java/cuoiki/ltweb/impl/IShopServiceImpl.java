package cuoiki.ltweb.impl;

import cuoiki.ltweb.services.IShopService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.ShopModel;

public class IShopServiceImpl implements IShopService{
	
   IShopDAO shopdao = new ShopDAOImpl();
   
   @Override
public boolean insertShop(ShopModel shop) {
	   return shopdao.insertShop(shop);
   }
   @Override
public List<ShopModel> getShopsByVendorId(long vendorid){
	   return shopdao.getShopsByVendorId(vendorid);
   }
   @Override
public ShopModel findById(long id) {
	   return shopdao.findById(id);
   }
	@Override
	public List<Map<String, Object>> getMonthlyRevenue(long shopId) throws SQLException, ClassNotFoundException {
		return shopdao.getMonthlyRevenue(shopId);
	}
	@Override
	public List<Map<String, Object>> getDailyRevenue(long shopId) throws SQLException, ClassNotFoundException{
		return shopdao.getDailyRevenue(shopId);
	}
	@Override
	public void update(ShopModel shop) {
		shopdao.update(shop);
	}
	@Override
	public List<ShopModel> findAll() {
	   return shopdao.findAll();
	}
	@Override
	public void delete(long idshop) {
		shopdao.delete(idshop);
	}


}
