package cuoiki.ltweb.impl;

import java.util.List;

import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.services.*;

public class IOrderDetailServiceImpl implements IOrderDetailService{
IOrderDetailDAO orderdetaildao = new OrderDetailDAOImpl();

@Override
public void insertOrderedProduct(OrderDetailModel ordProduct) {
	 orderdetaildao.insertOrderedProduct(ordProduct);
}
@Override
public List<OrderDetailModel>getAllOrderedProduct(long oid){
	return orderdetaildao.getAllOrderedProduct(oid);
}
@Override
public void updateOrderDetail(long id, String status) {
	orderdetaildao.updateOrderDetail(id, status);
}
@Override
public void delete(long id) {
	orderdetaildao.delete(id);
}

}
