package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.CategoryModel;

public interface ICategoryDAO {
	public List<CategoryModel> findAll();

	public CategoryModel getCategoryById(long cid);

	String getCategoryName(long catId);

	CategoryModel getCategoryByCategoryName(String categoryname);

	void update(CategoryModel category);

	void delete(CategoryModel category);

	void insert(CategoryModel category);
}
