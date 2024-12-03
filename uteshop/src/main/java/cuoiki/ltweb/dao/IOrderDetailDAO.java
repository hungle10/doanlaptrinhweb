package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.OrderDetailModel;

public interface IOrderDetailDAO {

	public void insertOrderedProduct(OrderDetailModel ordProduct);

	List<OrderDetailModel> getAllOrderedProduct(long oid);

	void updateOrderDetail(long id, String status);

	void delete(long id);

}
