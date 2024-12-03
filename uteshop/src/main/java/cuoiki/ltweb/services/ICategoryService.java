package cuoiki.ltweb.services;

import java.util.List;

import cuoiki.ltweb.models.CategoryModel;

public interface ICategoryService {

	public CategoryModel getCategoryById(long cid);

	String getCategoryName(long catId);

	List<CategoryModel> findAll();

	CategoryModel getCategoryByCategoryName(String categoryname);

	void update(CategoryModel category);

	void delete(CategoryModel category);

	void insert(CategoryModel category);

}
