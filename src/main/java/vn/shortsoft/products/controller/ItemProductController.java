package vn.shortsoft.products.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.response.ResponseObject;
import vn.shortsoft.products.services.ItemProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("v1/products")
public class ItemProductController {
    @Autowired
    ItemProductService itemProductService;

    @GetMapping()
    public ResponseEntity<ResponseObject> getListItemProduct(@RequestParam int pageNo,
            @RequestParam int pageSize, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(), "Successfully",
                itemProductService.getAllItemProduct(pageNo, pageSize, sortBy)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getItemProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(HttpStatus.OK.name(), "Successfully", itemProductService.getById(id)));
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> saveItemProduct(@RequestBody ItemProductDTO itemProductDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(), "Save Successfully",
                itemProductService.saveItem(itemProductDTO)));
    }

    @PatchMapping
    public ResponseEntity<ResponseObject> updateStatusItemProduct(@RequestParam String status, @RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(),
                "Update Status Success", itemProductService.updateStatusItem(id, status)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseObject> updateItemProduct(@PathVariable Long id,
            @RequestBody ItemProductDTO itemProductDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(), "Update Successfully",
                itemProductService.updateItem(id, itemProductDTO)));
    }

}
