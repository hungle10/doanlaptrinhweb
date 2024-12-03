package cuoiki.ltweb.impl;
import cuoiki.ltweb.models.CategoryModel;
import cuoiki.ltweb.services.*;

import java.util.List;

import cuoiki.ltweb.dao.*;

public class ICategoryServiceImpl implements ICategoryService{
	ICategoryDAO categorydao = new CategoryDAOImpl();
	@Override
	public CategoryModel getCategoryById(long cid) {
		return categorydao.getCategoryById(cid);
	}

	@Override
	public String getCategoryName(long catId) {
		return categorydao.getCategoryName(catId);
	}
	@Override
	public List<CategoryModel> findAll() {
		return categorydao.findAll();
	}
	@Override
	public CategoryModel getCategoryByCategoryName(String categoryname) {
		return categorydao.getCategoryByCategoryName(categoryname);
	}
	@Override
	public void update(CategoryModel category) {
		categorydao.update(category);
	}
	@Override
	public void delete(CategoryModel category) {
		categorydao.delete(category);
	}
	@Override
	public void insert(CategoryModel category) {
		categorydao.insert(category);
	}

}
