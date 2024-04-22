package vn.shortsoft.products.controller;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.services.ItemProductService;


@RestController
public class TestController {

    @Autowired
    private ItemProductService itemProductService;

    @PostMapping("/demo")
    public ResponseEntity<?> getMethodName(@RequestBody ItemProduct itemProduct) {
        return ResponseEntity.ok(itemProductService.saveItem(itemProduct));
    }
    
}
