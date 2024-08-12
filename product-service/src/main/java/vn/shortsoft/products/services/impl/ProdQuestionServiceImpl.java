package vn.shortsoft.products.services.impl;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.shortsoft.products.dao.ProdQuestionDao;
import vn.shortsoft.products.dto.ProdQuestionDto;
import vn.shortsoft.products.dto.convert.ProdQuestionConvert;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.services.ProdQuestionService;

@Service
public class ProdQuestionServiceImpl implements ProdQuestionService {

    @Autowired
    ProdQuestionDao prodQuestionDao;

    @Override
    public Long save(ProdQuestionDto prodQuestionDto) {
        ProdQuestion prodQuestion = ProdQuestionConvert.convertToProdQuestion(prodQuestionDto);
        return prodQuestionDao.save(prodQuestion);
    }

    @Override
    public Set<ProdQuestionDto> getAllQuestionByProductId(Long productId) {
        Set<ProdQuestion> prodQuestions = prodQuestionDao.getAllQuestionByProductId(productId);
        return prodQuestions.stream().map(t -> ProdQuestionConvert.convertToProdQuestionDto(t))
                .collect(Collectors.toSet());
    }

}
