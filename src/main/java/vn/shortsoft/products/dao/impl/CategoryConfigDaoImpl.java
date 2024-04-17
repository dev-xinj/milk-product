package vn.shortsoft.products.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import vn.shortsoft.products.dao.CategoryConfigDao;
import vn.shortsoft.products.model.CategoryConfig;
import vn.shortsoft.products.reponsitory.CategoryConfigRepository;

public class CategoryConfigDaoImpl implements CategoryConfigDao {

    @Autowired
    private CategoryConfigRepository categoryConfigRepository;

    @Override
    public CategoryConfig saveCategoryConfigDao(CategoryConfig categoryConfig) {
        return categoryConfigRepository.save(categoryConfig);
    }

    @Override
    public CategoryConfig findbyId(Long id) {
        Optional optCategory = categoryConfigRepository.findById(id);
        return optCategory.isPresent() ? (CategoryConfig) optCategory.get() : null;
    }

}
