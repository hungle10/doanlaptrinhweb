package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.CategoryModel;

public interface ICategoryDAO {
	public List<CategoryModel> findAll();

	public String getCategoryName(int catId);

	public CategoryModel getCategoryById(long cid);
}
