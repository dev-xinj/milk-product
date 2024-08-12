package vn.shortsoft.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.shortsoft.products.dto.ProdQuestionDto;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.services.ProdQuestionService;

@RestController
@RequestMapping("api/v1/question")
public class ProdQuestionController {

    @Autowired
    ProdQuestionService prodQuestionService;

    @PostMapping("save")
    public ResponseEntity<?> saveQuestion(@RequestBody List<ProdQuestionDto> prodQuestion){
        prodQuestion.forEach(q -> prodQuestionService.save(q));
        //prodQuestionService.save(prodQuestion)
        return ResponseEntity.ok().body("done");
    }

    // @GetMapping("{prodId}")
    // public ResponseEntity<?> getByProductId(@PathVariable Long prodId){
    //     List<ProdQuestionDto> prod = prodQuestionService.getProdQuestionByProductId(prodId);
    //     return ResponseEntity.ok().body(prodQuestionService.getProdQuestionByProductId(prodId));
    // }
    @GetMapping()
    public ResponseEntity<?> getDemo(){
        return ResponseEntity.ok().body(ProdQuestion.builder().build());
    }


}
