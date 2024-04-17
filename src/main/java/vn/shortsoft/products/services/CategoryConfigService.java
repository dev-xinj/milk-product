package vn.shortsoft.products.services;

import vn.shortsoft.products.model.CategoryConfig;

public interface CategoryConfigService {

    CategoryConfig saveCategoryConfig(CategoryConfig categoryConfig);

    CategoryConfig findById(Long id);
}
