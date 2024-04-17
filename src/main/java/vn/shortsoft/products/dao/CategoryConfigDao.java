package vn.shortsoft.products.dao;

import vn.shortsoft.products.model.CategoryConfig;

public interface CategoryConfigDao {
    CategoryConfig saveCategoryConfigDao(CategoryConfig categoryConfig);

    CategoryConfig findbyId(Long id);
}
