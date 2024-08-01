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

import vn.shortsoft.products.dto.ProdReviewDto;
import vn.shortsoft.products.services.ProdReviewService;

@RestController
@RequestMapping("api/v1/review")
public class ProdReviewController {
    @Autowired
    ProdReviewService prodReviewService;

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody List<ProdReviewDto> prodReviewDto) {
         prodReviewDto.stream().forEach(re -> prodReviewService.save(re));
         //prodReviewService.save(prodReviewDto)
        return ResponseEntity.ok().body("");
    }

    @GetMapping("{prodId}")
    public ResponseEntity<?> getAllByProductId(@PathVariable Long prodId) {
        return ResponseEntity.ok().body(prodReviewService.getAllReviewByProductId(prodId));
    }

}
