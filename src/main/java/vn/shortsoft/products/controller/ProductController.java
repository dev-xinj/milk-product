package vn.shortsoft.products.controller;

import java.sql.Timestamp;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.model.ProdReview;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.response.DataResponse;
import vn.shortsoft.products.services.ProductService;

@RestController
@RequestMapping("v1/products")
public class ProductController {
        @Autowired
        ProductService productService;

        @Autowired
        RedisTemplate<Object, Object> redisTemplate;
        private static final String STRING_KEY_PREFIX = "redi2read:strings:";

        @GetMapping()
        // @Cacheable(cacheNames = "getListItemProduct",key = "getList")
        public ResponseEntity<?> getProducts(@RequestParam int pageNo,
                        @RequestParam int pageSize, @RequestParam(required = false) String sortBy) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(productService.getAllProducts(pageNo, pageSize, sortBy));
        }

        @GetMapping("/search")
        // @Cacheable(value = "getSearchListItemProduct")
        public ResponseEntity<DataResponse> getProductsBySearch(@RequestParam int pageNo,
                        @RequestParam int pageSize, @RequestParam(required = false) String sortBy,
                        @RequestParam(required = false) String search) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(productService.getProductsBySearch(pageNo, pageSize, search, sortBy));
        }

        @GetMapping("{id}")
        public ResponseEntity<DataResponse> getProductById(@PathVariable Long id) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(productService.getById(id));
        }

        @GetMapping("demo")
        public ResponseEntity<?> demo() {
                Set<ProdReview> set = new HashSet<>();
                set.add(ProdReview.builder().build());
                return ResponseEntity.status(HttpStatus.OK)
                                .body(ProductDto.builder().prodReviews(set)
                                                .mfgDate(new Timestamp(new Date().getTime()))
                                                .properties(new HashMap<>())
                                                .build());
        }

        @PostMapping("save")
        public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(productService.saveProduct(productDto));

        }

        @PatchMapping
        public ResponseEntity<DataResponse> updateStatusProduct(@RequestParam String status,
                        @RequestParam Long id) {
                return ResponseEntity.status(HttpStatus.OK).body(productService.updateStatusProduct(id, status));
        }

        @PutMapping("{id}")
        public ResponseEntity<DataResponse> updateProduct(@PathVariable Long id,
                        @RequestBody ProductDto itemProductDTO) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(productService.saveProduct(itemProductDTO));
        }

        @DeleteMapping("{id}")
        public ResponseEntity<DataResponse> deleteById(@PathVariable Long id) {
                productService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(productService.deleteById(id));
        }

        @PostMapping("/redis")
        public Map.Entry<String, String> setString(@RequestBody Map.Entry<String, String> kvp) {
                // redisTemplate.opsForValue().set(STRING_KEY_PREFIX.toString() +
                // kvp.getKey().toString(),
                // kvp.getValue().toString());
                // System.out.println(STRING_KEY_PREFIX + kvp);
                // System.out.println(STRING_KEY_PREFIX);
                Set<Product> set = new HashSet<>();
                set.add(Product.builder().name("admin").build());
                set.add(Product.builder().name("customer").build());
                redisTemplate.opsForHash().put(STRING_KEY_PREFIX, kvp.getKey(), kvp.getValue());
                return kvp;
        }

        @GetMapping("/redis/{key}")
        public Map.Entry<String, String> getString(@PathVariable("key") String key) {
                String value = redisTemplate.opsForValue().get(STRING_KEY_PREFIX + key).toString();

                if (value == null) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "key not found");
                }
                return new SimpleEntry<String, String>(key, value);
        }

}
