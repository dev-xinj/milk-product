package vn.shortsoft.products.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.exception.ResourceNotFoundException;
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
        return itemProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Item Product by id " + id));
    }

    @Override
    public ItemProduct updateItem(Long id, ItemProduct itemProduct) {
        Optional<ItemProduct> opt = itemProductRepository.findById(id);
        if (opt.isPresent()) {
            opt.get().setItemCode(itemProduct.getItemCode());
            opt.get().setItemName(itemProduct.getItemName());
        }
        return itemProductRepository.save(opt.get());
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
    public void updateStatusItem(Long id, String status) {
        try {
            itemProductRepository.updateByStatus(id, status);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not Update Status: " + status);
        }
    }

}
