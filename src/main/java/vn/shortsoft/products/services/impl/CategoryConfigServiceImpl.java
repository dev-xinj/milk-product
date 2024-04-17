package vn.shortsoft.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.shortsoft.products.dao.CategoryConfigDao;
import vn.shortsoft.products.model.CategoryConfig;
import vn.shortsoft.products.services.CategoryConfigService;

@Service
public class CategoryConfigServiceImpl implements CategoryConfigService {

    @Autowired
    private CategoryConfigDao categoryConfigDao;

    @Override
    public CategoryConfig saveCategoryConfig(CategoryConfig categoryConfig) {
        return categoryConfigDao.saveCategoryConfigDao(categoryConfig);
    }

    @Override
    public CategoryConfig findById(Long id) {
        return categoryConfigDao.findbyId(id);
    }

}
