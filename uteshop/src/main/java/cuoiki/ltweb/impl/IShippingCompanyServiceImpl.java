package cuoiki.ltweb.impl;

import cuoiki.ltweb.dao.IShippingCompanyDAO;
import cuoiki.ltweb.models.ShippingCompanyModel;
import cuoiki.ltweb.services.IShippingCompanyService;

public class IShippingCompanyServiceImpl implements IShippingCompanyService{
IShippingCompanyDAO companydao = new ShippingCompanyDAOImpl(); 
	
	@Override
	public long getIdShippingCompanyByName(String companyname) {
		return companydao.getIdShippingCompanyByName(companyname);
	}
	@Override
	public ShippingCompanyModel findById(long id) {
		return companydao.findById(id);
	}

}
