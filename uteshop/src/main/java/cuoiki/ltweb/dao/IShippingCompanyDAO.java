package cuoiki.ltweb.dao;

import cuoiki.ltweb.models.ShippingCompanyModel;

public interface IShippingCompanyDAO {


	public long getIdShippingCompanyByName(String companyname);

	ShippingCompanyModel findById(long id);

}
