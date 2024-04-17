package vn.shortsoft.products.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.services.ItemProductService;

@Service
public class ItemProductServiceImpl implements ItemProductService {

    @Autowired
    private ItemProductDao itemProductDao;

    @Override
    public List<ItemProduct> getAllItemProduct() {
        return itemProductDao.getAllItemProduct();
    }

    @Override
    public ItemProduct getById(Long id) {

        return itemProductDao.getById(id);
    }

    @Override
    @Transactional
    public ItemProduct saveItem(ItemProduct itemProduct) {
        return itemProductDao.saveItem(itemProduct);
    }

}
