package vn.shortsoft.products.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.reponsitory.ItemProductRepository;

@Component
public class ItemProductDaoImpl implements ItemProductDao {
    @Autowired
    private ItemProductRepository itemProductRepository;

    @Override
    public List<ItemProduct> getAllItemProduct() {

        return itemProductRepository.findAll();
    }

    @Override
    public ItemProduct getById(Long id) {
        Optional optItemProduct = itemProductRepository.findById(id);
        return optItemProduct.isPresent() ? (ItemProduct) optItemProduct.get() : null;
    }

    @Override
    public ItemProduct saveItem(ItemProduct itemProduct) {
        return itemProductRepository.save(itemProduct);
    }

}
