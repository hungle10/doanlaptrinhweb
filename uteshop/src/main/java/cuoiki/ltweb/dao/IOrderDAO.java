package cuoiki.ltweb.dao;

import java.sql.Timestamp;
import java.util.List;

import cuoiki.ltweb.models.OrderModel;

public interface IOrderDAO {

	public boolean insertOrder(OrderModel order);

	public OrderModel getOrderByUserIdAndCreatedAt(long user_id, Timestamp created_at);

	public List<OrderModel> getOrders(long user_id);

	List<OrderModel> getAllOrders();

	void updateOrder(long id, String status);
}
