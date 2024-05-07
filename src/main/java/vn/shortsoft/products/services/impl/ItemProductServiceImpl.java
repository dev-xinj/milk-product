package vn.shortsoft.products.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.dto.convert.ItemProductConvert;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.response.PageResponse;
import vn.shortsoft.products.services.ItemProductRedisService;
import vn.shortsoft.products.services.ItemProductService;

@Service
public class ItemProductServiceImpl implements ItemProductService {

    @Autowired
    private ItemProductDao itemProductDao;
    @Autowired
    private ItemProductRedisService itemProductRedisService;

    @Override
    @Cacheable(cacheNames = "listItemProduct",key = "'listKey'")
    public PageResponse<?> getAllItemProduct(int pageNo, int pageSize, String sortBy) {
        int p = pageNo;
        if (pageNo > 0) {
            pageNo = pageNo - 1;
        }
        List<ItemProductDTO> listItemProductDTO = new ArrayList<>();
        Page<ItemProduct> listItemProduct = itemProductDao.getAllItemProduct(pageNo, pageSize, sortBy);

        if (!listItemProduct.isEmpty()) {
            for (ItemProduct itemProduct : listItemProduct) {
                listItemProductDTO.add(ItemProductConvert.convertToItemProductDTO(itemProduct));
            }
            itemProductRedisService.saveAll(listItemProductDTO, pageNo, pageSize, sortBy, sortBy);
        }
        return PageResponse.builder()
                .pageNo(p)
                .pageSize(pageSize)
                .pageTotal(listItemProduct.getTotalPages())
                .data(listItemProductDTO) // Convert Thủ công
                // .data(listItemProduct.stream() //Dùng lamda
                // .map(itemProduct -> ItemProductConvert.convertToItemProductDTO(itemProduct))
                // .toList())
                .build();

    }

    @Override
    @Cacheable(cacheNames = "product", key = "#id")
    public ItemProductDTO getById(Long id) {
        // ItemProductDTO item = itemProductRedisService.getById(id);
        // if(item != null){
        //     return item;
        // }
        return ItemProductConvert.convertToItemProductDTO(itemProductDao.getById(id));
    }

    @Override
    @Transactional
    public ItemProductDTO saveItem(ItemProductDTO itemProductDto) {
        ItemProduct itemProduct = ItemProductConvert.convertToItemProduct(itemProductDto);
        itemProduct = itemProductDao.saveItem(itemProduct);
        itemProductDto = ItemProductConvert.convertToItemProductDTO(itemProduct);
        // itemProductRedisService.save(itemProductDto);
        return itemProductDto;
    }

    @Override
    @CachePut(cacheNames = "product", key = "#id")
    public ItemProductDTO updateItem(Long id, ItemProductDTO itemProductDTO) {
        itemProductDao.updateItem(id, ItemProductConvert.convertToItemProduct(itemProductDTO));
        return itemProductDTO;
    }

    @Override
    @CacheEvict(cacheNames = "product", key = "#id")
    public void deleteById(Long id) {
        itemProductDao.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = "product", key = "#id")
    public int updateStatusItem(Long id, String status) {
        itemProductDao.updateStatusItem(id, status);
        return 1;
    }

    @Override
    public List<ItemProduct> getListEmployeeBySearch(int pageNo, int pageSize, String search,
            String sortBy) {
        return itemProductDao.getListEmployeeBySearch(pageNo, pageSize, search, sortBy);
    }

}
