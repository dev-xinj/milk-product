package vn.shortsoft.products.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.shortsoft.products.dao.ProdQuestionDao;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.repository.ProdQuestionRepository;

@Component
public class ProdQuestionDaoImpl implements ProdQuestionDao {

    @Autowired
    ProdQuestionRepository prodQuestionRepository;

    @Override
    public Long save(ProdQuestion prodQuestion) {
        Long id = prodQuestionRepository.save(prodQuestion).getId();
        return id;
    }

    @Override
    public List<ProdQuestion> getProdQuestionByProductId(Long productId) {
        Optional<List<ProdQuestion>> optList = prodQuestionRepository.findByProductId(productId);
        if (optList.isPresent()) {
            return optList.get();
        }
        return null;
    }

}
