package cuoiki.ltweb.impl;
import cuoiki.ltweb.models.CategoryModel;
import cuoiki.ltweb.services.*;
import cuoiki.ltweb.dao.*;

public class ICategoryServiceImpl implements ICategoryService{
	ICategoryDAO categorydao = new CategoryDAOImpl();
	@Override
	public CategoryModel getCategoryById(long cid) {
		return categorydao.getCategoryById(cid);
	}

}
