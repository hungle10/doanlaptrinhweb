package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.ShippingCompanyModel;

public interface IShippingCompanyService {

	public long getIdShippingCompanyByName(String companyname);

	ShippingCompanyModel findById(long id);

	List<ShippingCompanyModel> getAllShippingCompany();

	void update(ShippingCompanyModel shippingunit);

	void insert(ShippingCompanyModel shippingunit);

	void delete(long id);

}
