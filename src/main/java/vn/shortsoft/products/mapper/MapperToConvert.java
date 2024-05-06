package vn.shortsoft.products.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.shortsoft.products.dto.ItemProductDTO;

public class MapperToConvert {

    public static String ConvertToJson(ItemProductDTO itemProductDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String carAsString;
        try {
            carAsString = objectMapper.writeValueAsString(itemProductDTO);
            return carAsString;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
