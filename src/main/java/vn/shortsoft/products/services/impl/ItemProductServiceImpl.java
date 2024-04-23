package vn.shortsoft.products.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.dto.convert.ItemProductConvert;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.services.ItemProductService;

@Service
public class ItemProductServiceImpl implements ItemProductService {

    @Autowired
    private ItemProductDao itemProductDao;

    @Override
    public List<ItemProductDTO> getAllItemProduct(int pageNo, int pageSize) {
        List<ItemProduct> listItemProduct = itemProductDao.getAllItemProduct(pageNo, pageSize);
        return listItemProduct.stream().map(itemProduct -> ItemProductConvert.convertToItemProductDTO(itemProduct))
                .toList();
    }

    @Override
    public ItemProductDTO getById(Long id) {
        return ItemProductConvert.convertToItemProductDTO(itemProductDao.getById(id));
    }

    @Override
    @Transactional
    public Long saveItem(ItemProductDTO itemProductDto) {
        ItemProduct itemProduct = ItemProductConvert.convertToItemProduct(itemProductDto);
        return itemProductDao.saveItem(itemProduct).getId();
    }

    @Override
    public ItemProductDTO updateItem(Long id, ItemProductDTO itemProductDTO) {
        itemProductDao.updateItem(id, ItemProductConvert.convertToItemProduct(itemProductDTO));
        return itemProductDTO;
    }

    @Override
    public void deleteById(Long id) {
        itemProductDao.deleteById(id);
    }

    @Override
    public int updateStatusItem(Long id, String status) {
        itemProductDao.updateStatusItem(id, status);
        return 1;
    }

}
