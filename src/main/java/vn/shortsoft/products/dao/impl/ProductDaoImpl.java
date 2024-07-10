package vn.shortsoft.products.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.products.dao.ProductDao;
import vn.shortsoft.products.exception.ResourceNotFoundException;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.model.ProdReview;
import vn.shortsoft.products.model.ProdSale;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.repository.ProductRepository;

@Component
@Log4j2
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
            log.info("vao day");
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
    public List<Product> getProductsBySearch(int pageNo, int pageSize, String search, String sortBy) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Product> root = criteriaQuery.from(Product.class);

        // joinReview.get("product").get("id");
        if (StringUtils.hasLength(sortBy)) {
            String nameSort = extractSortBy(sortBy, t -> t.group(1));
            String typeSort = extractSortBy(sortBy, t -> t.group(3));
            if (typeSort.toLowerCase().equals("asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(nameSort)));
            } else if (typeSort.toLowerCase().equals("desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(nameSort)));
            } else {
                log.info("Du lieu khong hop le");

            }
        }
        // join table
        Join<Product, ProdReview> joinReview = root.join("prodReviews", JoinType.LEFT);
        Join<Product, ProdQuestion> joinQuesion = root.join("prodQuestions", JoinType.LEFT);
        Join<Product, ProdSale> joinSale = root.join("prodSales", JoinType.LEFT);

        criteriaQuery.multiselect(root, criteriaBuilder.avg(joinReview.get("star")).alias("avg_review"), // trung bình
                                                                                                         // số sao đánh
                                                                                                         // giá
                criteriaBuilder.count(joinReview).alias("total_review"), // tổng số lượt đánh giá
                criteriaBuilder.count(joinSale).alias("total_sale")); // tổng số lượt bán
        criteriaQuery.groupBy(root.get("id"));
        // Predicate condition = criteriaBuilder.(root.get("id"));
        if (StringUtils.hasLength(search)) {
            criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                    String.format("%%%s%%", search.toLowerCase())));
        }
        List<Object[]> results = entityManager.createQuery(criteriaQuery)
                .getResultList();
        List<Product> products = new ArrayList<>();
        results.forEach(r -> {
            products.add((Product) r[0]);
            // log.info(r[1]);
            // log.info(r[2]);
            // log.info(r[3]);
        });
        return products;
    }

    private <T> T extractSortBy(String sortBy, Function<Matcher, T> fMatcher) {
        String regex = "(\\w+?)(:)(desc|asc)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(sortBy);
        if (matcher.find()) {
            return fMatcher.apply(matcher);
        }
        return null;
    }
}