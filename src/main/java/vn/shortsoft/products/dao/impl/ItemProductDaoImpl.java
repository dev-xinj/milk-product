package vn.shortsoft.products.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.reponsitory.ItemProductRepository;

@Component
public class ItemProductDaoImpl implements ItemProductDao {
    @Autowired
    private ItemProductRepository itemProductRepository;

    @Override
    public List<ItemProduct> getAllItemProduct(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return itemProductRepository.findAll(pageable).toList();
    }

    @Override
    public ItemProduct getById(Long id) {
        Optional optItemProduct = itemProductRepository.findById(id);
        return optItemProduct.isPresent() ? (ItemProduct) optItemProduct.get() : null;
    }

    @Override
    public void deleteById(Long id) {
        itemProductRepository.deleteById(id);
    }

    @Override
    public ItemProduct saveItem(ItemProduct itemProduct) {
        return itemProductRepository.save(itemProduct);
    }

    @Override
    public int updateStatusItem(Long id, String status) {
        if (getById(id) != null) {
            itemProductRepository.updateByStatus(id, status);
            return 1;
        }
        return 0;
    }

}
