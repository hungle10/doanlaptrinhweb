package cuoiki.ltweb.services;

import cuoiki.ltweb.models.ShippingCompanyModel;

public interface IShippingCompanyService {

	public long getIdShippingCompanyByName(String companyname);

	ShippingCompanyModel findById(long id);

}
