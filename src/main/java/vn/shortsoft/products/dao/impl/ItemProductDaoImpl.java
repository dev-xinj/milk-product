package vn.shortsoft.products.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ItemProductDao;
import vn.shortsoft.products.exception.ResourceNotFoundException;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.reponsitory.ItemProductRepository;

@Component
public class ItemProductDaoImpl implements ItemProductDao {
    @Autowired
    private ItemProductRepository itemProductRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ItemProduct> getAllItemProduct(int pageNo, int pageSize, String sortBy) {
        Pageable pageable;
        if (checkRegexSort(sortBy) != null) {
            pageable = PageRequest.of(pageNo, pageSize, checkRegexSort(sortBy));
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }
        return itemProductRepository.findAll(pageable);
    }

    private Sort checkRegexSort(String sortBy) {
        String regex = "(\\w+?)(:)(desc|asc)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        Map<String, Sort.Direction> map = new HashMap<>();
        if (StringUtils.hasLength(sortBy)) {
            map.put("desc", Sort.Direction.DESC);
            map.put("asc", Sort.Direction.ASC);
            matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                return Sort.by(map.get(matcher.group(3)), matcher.group(1));
            }
        }
        return null;
    }

    @Override
    public ItemProduct getById(Long id) {
        return itemProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Item Product by id " + id));
    }

    @Override
    public ItemProduct updateItem(Long id, ItemProduct itemProduct) {
        ItemProduct opt = getById(id);
        try {
            return itemProductRepository.save(setUpdateItemProduct(opt, itemProduct));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
    @Transactional
    public void updateStatusItem(Long id, String status) {
        try {
            itemProductRepository.updateByStatus(id, status);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not Update Status: " + status);
        }
    }

    private ItemProduct setUpdateItemProduct(ItemProduct itemProductOld, ItemProduct itemProductNew) {
        if (StringUtils.hasLength(itemProductNew.getItemCode())) {
            itemProductOld.setItemCode(itemProductNew.getItemCode());
        }
        if (StringUtils.hasLength(itemProductNew.getItemName())) {
            itemProductOld.setItemName(itemProductNew.getItemName());
        }
        if (StringUtils.hasLength(itemProductNew.getSku())) {
            itemProductOld.setSku(itemProductNew.getSku());
        }
        if (StringUtils.hasLength(itemProductNew.getType())) {
            itemProductOld.setType(itemProductNew.getType());
        }
        if (StringUtils.hasLength(itemProductNew.getStatus())) {
            itemProductOld.setStatus(itemProductNew.getStatus());
        }
        return itemProductOld;

    }

    // @Override
    // public List<ItemProduct> getListEmployeeBySearch(int pageNo, int pageSize,
    // String search, String sortBy) {
    // return entityManagerRepository.getListEmployeeBySearch(pageNo, pageSize,
    // search, sortBy);

    // }
    @Override
    public List<ItemProduct> getListEmployeeBySearch(int pageNo, int pageSize, String search, String sortBy) {

        StringBuilder sql = new StringBuilder("SELECT i FROM ItemProduct i");
        TypedQuery<ItemProduct> query = entityManager.createQuery(sql.toString(),ItemProduct.class);
        query.setFirstResult(pageNo);
        query.setMaxResults(pageSize);

        List<ItemProduct> listItemProduct = query.getResultList();
        // int totalPage = listItemProduct.size() / pageSize;
        return listItemProduct;
    }

}
