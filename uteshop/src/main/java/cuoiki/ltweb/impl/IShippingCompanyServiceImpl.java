package cuoiki.ltweb.impl;

import java.util.List;

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
	@Override
	public List<ShippingCompanyModel> getAllShippingCompany(){
		return companydao.getAllShippingCompany();
	}
	@Override
	public void update(ShippingCompanyModel shippingunit) {
		companydao.update(shippingunit);
	}
	@Override
	public void insert(ShippingCompanyModel shippingunit) {
		companydao.insert(shippingunit);
	}
	@Override
	public void delete(long id) {
		companydao.delete(id);
	}

}
