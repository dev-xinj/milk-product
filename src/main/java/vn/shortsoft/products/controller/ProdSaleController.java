// package vn.shortsoft.products.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import vn.shortsoft.products.dto.ProdSaleDto;
// import vn.shortsoft.products.services.ProdSaleService;

// import java.util.List;

// @RestController
// @RequestMapping("api/v1/sale")
// public class ProdSaleController {

//     @Autowired
//     ProdSaleService prodSaleService;

//     @PostMapping("save")
//     public ResponseEntity<?> save(@RequestBody List<ProdSaleDto> prodSaleDto) {
//         prodSaleDto.forEach(t->prodSaleService.saveSale(t));
//         //prodSaleService.saveSale(prodSaleDto)
//         return ResponseEntity.ok().body("done");
//     }
// }
