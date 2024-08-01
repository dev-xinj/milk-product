package vn.shortsoft.products.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.dto.convert.ProductConvert;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.repository.ProductRedisRepository;
import vn.shortsoft.products.response.PageResponse;
import vn.shortsoft.products.services.ProductRedisService;

@Service
public class ProductRedisServiceImpl implements ProductRedisService {

    @Autowired
    ProductRedisRepository redisRepository;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void clear(String key) {
        redisTemplate.delete(key);

    }

    @Override
    public ProductDto getById(Long id) {
        ObjectMapper ob = new ObjectMapper();
        try {
            return ob.readValue(redisTemplate.opsForValue().get("product::" + id).toString(), ProductDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<ProductDto> getProductDtos(int pageNo, int pageSize, String search, String sortBy) {
        String keyString = String.format("%d%d%s%s", pageNo, pageSize, search, sortBy);
        ObjectMapper ob = new ObjectMapper();
        try {
            Set<ProductDto> list = ob.readValue(redisTemplate.opsForValue().get(keyString).toString(),
                    new TypeReference<Set<ProductDto>>() {
                    });
            return list;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(ProductDto productDto) {
        ObjectMapper ob = new ObjectMapper();
        try {
            redisTemplate.opsForValue().set("product::" + productDto.getId(),
                    ob.writeValueAsString(productDto));
            redisRepository.save(ProductConvert.convertToProduct(productDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ;

    }

    @Override
    public void saveByKey(ProductDto productDto) {
        // ObjectMapper ob = new ObjectMapper();
        // try {
        // redisTemplate.opsForValue().set("products::listKey", productDto);
        // // ob.writeValueAsString(productDto));
        // } catch (JsonProcessingException e) {
        // e.printStackTrace();
        // };
        redisTemplate.opsForValue().set("products::listKey", productDto);

    }

    @Override
    public void update(ProductDto productDto) {
        redisTemplate.opsForValue().set(productDto.getId(), productDto);

    }

    @Override
    public void saveAll(PageResponse pageResponse, int pageNo, int pageSize, String search,
            String sortBy) {
        String keyString = String.format("%d%d%s%s", pageNo, pageSize, search, sortBy);
        ObjectMapper ob = new ObjectMapper();
        try {
            redisTemplate.opsForValue().set(keyString, ob.writeValueAsString(pageResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
