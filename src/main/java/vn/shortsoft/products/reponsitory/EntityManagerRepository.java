package vn.shortsoft.products.reponsitory;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import vn.shortsoft.products.model.ItemProduct;

@Repository
public class EntityManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemProduct> getListEmployeeBySearch(int pageNo, int pageSize, String search, String sortBy) {

        StringBuilder sql = new StringBuilder("SELECT ItemProduct FROM ItemProduct");
        Query query = entityManager.createQuery(sql.toString(),ItemProduct.class);
        query.setFirstResult(pageNo);
        query.setMaxResults(pageSize);

        List<ItemProduct> listItemProduct = query.getResultList();
        // int totalPage = listItemProduct.size() / pageSize;
        return listItemProduct;
    }

}
