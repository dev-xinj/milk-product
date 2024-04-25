package vn.shortsoft.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.dto.convert.ItemProductConvert;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.response.PageResponse;
import vn.shortsoft.products.services.ItemProductService;

@Service
public class ItemProductServiceImpl implements ItemProductService {

    @Autowired
    private ItemProductDao itemProductDao;

    @Override
    public PageResponse<?> getAllItemProduct(int pageNo, int pageSize, String sortBy) {
        int p = pageNo;
        if (pageNo > 0) {
            pageNo = pageNo - 1;
        }
        Page<ItemProduct> listItemProduct = itemProductDao.getAllItemProduct(pageNo, pageSize, sortBy);
        return PageResponse.builder()
        .pageNo(p)
        .pageSize(pageSize)
        .pageTotal(listItemProduct.getTotalPages())
        .data(listItemProduct.stream().map(itemProduct -> ItemProductConvert.convertToItemProductDTO(itemProduct))
                .toList()).build();

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
