package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.OrderDetailModel;

public interface IOrderDetailService {

	void insertOrderedProduct(OrderDetailModel ordProduct);

	List<OrderDetailModel> getAllOrderedProduct(long oid);

	void updateOrderDetail(long id, String status);

	void delete(long id);

}
