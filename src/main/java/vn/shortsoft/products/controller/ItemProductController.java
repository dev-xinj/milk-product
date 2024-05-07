package vn.shortsoft.products.controller;

import java.util.AbstractMap.SimpleEntry;
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

import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.mapper.MapperToConvert;
import vn.shortsoft.products.model.Roles;
import vn.shortsoft.products.reponsitory.RoleRepository;
import vn.shortsoft.products.response.ResponseObject;
import vn.shortsoft.products.services.ItemProductService;
import vn.shortsoft.products.services.impl.CreateRole;

@RestController
@RequestMapping("v1/products")
public class ItemProductController {
        @Autowired
        ItemProductService itemProductService;

        @Autowired
        RedisTemplate<Object, Object> redisTemplate;
        private static final String STRING_KEY_PREFIX = "redi2read:strings:";
        @Autowired
        private RoleRepository roleRepository;
        @Autowired
        private CreateRole createRole;

        @GetMapping()
        // @Cacheable(cacheNames = "getListItemProduct",key = "getList")
        public ResponseEntity<ResponseObject> getListItemProduct(@RequestParam int pageNo,
                        @RequestParam int pageSize, @RequestParam(required = false) String sortBy) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseObject(HttpStatus.OK.name(), "Successfully",
                                                itemProductService.getAllItemProduct(pageNo, pageSize, sortBy)));
        }
        @GetMapping("/mapper")
        public String getMethodName(@RequestBody ItemProductDTO param) {
            return MapperToConvert.ConvertToJson(param);
        }
        
        @GetMapping("/search")
        // @Cacheable(value = "getSearchListItemProduct")
        public ResponseEntity<ResponseObject> getListEmployeeBySearch(@RequestParam int pageNo,
                        @RequestParam int pageSize, @RequestParam(required = false) String sortBy,
                        @RequestParam(required = false) String search) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(),
                                "Successfully",
                                itemProductService.getListEmployeeBySearch(pageNo, pageSize, search, sortBy)));
        }

        @GetMapping("{id}")
        public ResponseEntity<ResponseObject> getItemProductById(@PathVariable Long id) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseObject(HttpStatus.OK.name(), "Successfully",
                                                itemProductService.getById(id)));
        }

        @PostMapping()
        public ResponseEntity<ResponseObject> saveItemProduct(@RequestBody ItemProductDTO itemProductDTO) {
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ResponseObject(HttpStatus.CREATED.name(), "Save Successfully",
                                                itemProductService.saveItem(itemProductDTO)));
        }

        @PatchMapping
        public ResponseEntity<ResponseObject> updateStatusItemProduct(@RequestParam String status,
                        @RequestParam Long id) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(),
                                "Update success status " + status, itemProductService.updateStatusItem(id, status)));
        }

        @PutMapping("{id}")
        public ResponseEntity<ResponseObject> updateItemProduct(@PathVariable Long id,
                        @RequestBody ItemProductDTO itemProductDTO) {
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseObject(HttpStatus.OK.name(), "Update Successfully",
                                                itemProductService.updateItem(id, itemProductDTO)));
        }

        @DeleteMapping("{id}")
        public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id){
                itemProductService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.name(), "Delete Success"));
        }

        @PostMapping("/redis")
        public Map.Entry<String, String> setString(@RequestBody Map.Entry<String, String> kvp) {
                // redisTemplate.opsForValue().set(STRING_KEY_PREFIX.toString() +
                //                 kvp.getKey().toString(),
                //                 kvp.getValue().toString());
                // System.out.println(STRING_KEY_PREFIX + kvp);
                // System.out.println(STRING_KEY_PREFIX);
                Set<Roles> set = new HashSet<>();
                set.add(Roles.builder().name("admin").build());
                set.add(Roles.builder().name("customer").build());
                redisTemplate.opsForHash().put(STRING_KEY_PREFIX,kvp.getKey(),kvp.getValue());
                try {
                        createRole.run(null);

                } catch (Exception e) {
                        e.printStackTrace();
                }
                // Roles r = createRole.findFirstByName();
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
