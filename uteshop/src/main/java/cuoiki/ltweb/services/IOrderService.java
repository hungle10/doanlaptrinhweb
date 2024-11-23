package cuoiki.ltweb.services;

import java.sql.Timestamp;
import java.util.List;

import cuoiki.ltweb.models.OrderModel;

public interface IOrderService {

	public boolean insertOrder(OrderModel order);

	public OrderModel getOrderByUserIdAndCreatedAt(long user_id, Timestamp created_at);

    public List<OrderModel> getOrders(long user_id);

}
