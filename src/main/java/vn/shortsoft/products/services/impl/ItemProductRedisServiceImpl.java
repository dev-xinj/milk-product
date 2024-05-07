package vn.shortsoft.products.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.reponsitory.ItemProductRedisRepository;
import vn.shortsoft.products.services.ItemProductRedisService;

@Service
public class ItemProductRedisServiceImpl implements ItemProductRedisService {

    @Autowired
    ItemProductRedisRepository redisRepository;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void clear(String key) {
        redisTemplate.delete(key);

    }

    @Override
    public ItemProductDTO getById(Long id) {
        ObjectMapper ob = new ObjectMapper();
        try {
            return ob.readValue(redisTemplate.opsForValue().get("product::"+id).toString(), ItemProductDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemProductDTO> getListItemProductDTO(int pageNo, int pageSize, String search, String sortBy) {
        String keyString = String.format("%d%d%s%s", pageNo, pageSize, search, sortBy);
        ObjectMapper ob = new ObjectMapper();
        try {
            List<ItemProductDTO> list = ob.readValue(redisTemplate.opsForValue().get(keyString).toString(),
                    new TypeReference<List<ItemProductDTO>>() {
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
    public void save(ItemProductDTO itemProductDTO) {
        ObjectMapper ob = new ObjectMapper();
        try {
            redisTemplate.opsForValue().set("product::"+itemProductDTO.getId(), ob.writeValueAsString(itemProductDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ;

    }

    @Override
    public void update(ItemProductDTO itemProductDTO) {
        redisTemplate.opsForValue().set(itemProductDTO.getId(), itemProductDTO);

    }

    @Override
    public void saveAll(List<ItemProductDTO> listItemProductDto, int pageNo, int pageSize, String search,
            String sortBy) {
        String keyString = String.format("%d%d%s%s", pageNo, pageSize, search, sortBy);
        ObjectMapper ob = new ObjectMapper();
        try {
            redisTemplate.opsForValue().set(keyString, ob.writeValueAsString(listItemProductDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
