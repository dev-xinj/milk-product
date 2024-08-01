package vn.shortsoft.products.dao.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import vn.shortsoft.products.dao.ProdQuestionDao;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.repository.ProdQuestionRepository;

@Component
public class ProdQuestionDaoImpl implements ProdQuestionDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ProdQuestionRepository prodQuestionRepository;

    @Override
    public Long save(ProdQuestion prodQuestion) {
        Long id = prodQuestionRepository.save(prodQuestion).getId();
        return id;
    }

    @Override
    public Set<ProdQuestion> getAllQuestionByProductId(Long productId) {
        Optional<Set<ProdQuestion>> optList = prodQuestionRepository.findByProductId(productId);
        if (optList.isPresent()) {
            return optList.get();
        }
        return null;
    }

    @Override
    public Set<ProdQuestion> getQuestionByProductId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdQuestion> criteriaQuery = criteriaBuilder.createQuery(ProdQuestion.class);
        Root<ProdQuestion> root = criteriaQuery.from(ProdQuestion.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("product").get("id"), id));
        Set<ProdQuestion> prodQuestions = entityManager.createQuery(criteriaQuery).getResultList().stream()
                .collect(Collectors.toSet());

        return prodQuestions;

    }

}
