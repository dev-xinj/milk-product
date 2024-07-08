package vn.shortsoft.products.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.shortsoft.products.model.Product;

public interface ProductDao {
    Product saveProduct(Product itemProduct);

    Page<Product> getAllProducts(int pageNo, int pageSize, String sortBy);

    List<Product> getProductsBySearch(int pageNo, int pageSize, String search, String sortBy);

    void updateStatusProduct(Long id, String status);

    void deleteById(Long id);

    Product getById(Long id);

}
