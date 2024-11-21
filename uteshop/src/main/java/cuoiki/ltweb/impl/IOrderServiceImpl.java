package cuoiki.ltweb.impl;

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

}
