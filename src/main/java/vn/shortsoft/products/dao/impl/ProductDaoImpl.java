package vn.shortsoft.products.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ProductDao;
import vn.shortsoft.products.exception.ResourceNotFoundException;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.reponsitory.ProductRepository;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Product> getAllProducts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable;
        if (checkRegexSort(sortBy) != null) {
            pageable = PageRequest.of(pageNo, pageSize, checkRegexSort(sortBy));
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }
        return productRepository.findAll(pageable);
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
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Item Product by id " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateStatusProduct(Long id, String status) {
        try {
            productRepository.updateByStatus(id, status);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not Update Status: " + status);
        }
    }

    @Override
    public Set<Product> getProductsBySearch(int pageNo, int pageSize, String search, String sortBy) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), "search"));
        Set<Product> products = entityManager.createQuery(criteriaQuery).getResultList().stream()
                .collect(Collectors.toSet());
        return products;
    }

}
