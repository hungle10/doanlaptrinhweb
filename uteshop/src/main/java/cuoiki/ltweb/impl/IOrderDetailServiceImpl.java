package cuoiki.ltweb.impl;

import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.OrderDetailModel;
import cuoiki.ltweb.services.*;

public class IOrderDetailServiceImpl implements IOrderDetailService{
IOrderDetailDAO orderdetaildao = new OrderDetailDAOImpl();

@Override
public void insertOrderedProduct(OrderDetailModel ordProduct) {
	 orderdetaildao.insertOrderedProduct(ordProduct);
}

}
