package cuoiki.ltweb.impl;

import java.sql.Timestamp;
import java.util.List;

import cuoiki.ltweb.configs.DBConnectSQLServer;
import cuoiki.ltweb.services.IOrderService;
import cuoiki.ltweb.impl.*;
import cuoiki.ltweb.models.OrderModel;
import cuoiki.ltweb.dao.*;

public class IOrderServiceImpl extends DBConnectSQLServer implements IOrderService{
	IOrderDAO orderdao = new OrderDAOImpl();
	
	@Override
	public boolean insertOrder(OrderModel order) {
		return orderdao.insertOrder(order);
	}
	@Override
	public OrderModel getOrderByUserIdAndCreatedAt(long user_id,Timestamp created_at) {
		return orderdao.getOrderByUserIdAndCreatedAt(user_id, created_at);
	}
	@Override
	public List<OrderModel> getOrders(long user_id){
		return orderdao.getOrders(user_id);
	}
	@Override
	public List<OrderModel> getAllOrders(){
		return orderdao.getAllOrders();
	}
	@Override
	public void updateOrder(long id, String status) {
		orderdao.updateOrder(id, status);
	}

}
