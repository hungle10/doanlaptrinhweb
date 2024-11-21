package cuoiki.ltweb.impl;

import cuoiki.ltweb.dao.IShippingCompanyDAO;
import cuoiki.ltweb.services.IShippingCompanyService;

public class IShippingCompanyServiceImpl implements IShippingCompanyService{
IShippingCompanyDAO companydao = new ShippingCompanyDAOImpl(); 
	
	@Override
	public int getIdShippingCompanyByName(String companyname) {
		return companydao.getIdShippingCompanyByName(companyname);
	}

}
