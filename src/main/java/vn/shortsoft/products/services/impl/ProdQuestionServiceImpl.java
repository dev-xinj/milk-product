package vn.shortsoft.products.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.shortsoft.products.dao.ProdQuestionDao;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.services.ProdQuestionService;

@Service
public class ProdQuestionServiceImpl implements ProdQuestionService {

    @Autowired
    ProdQuestionDao prodQuestionDao;

    @Override
    public Long save(ProdQuestion prodQuestion) {
        return prodQuestionDao.save(prodQuestion);
    }

    @Override
    public List<ProdQuestion> getProdQuestionByProductId(Long productId) {
        return prodQuestionDao.getProdQuestionByProductId(productId);
    }

}
