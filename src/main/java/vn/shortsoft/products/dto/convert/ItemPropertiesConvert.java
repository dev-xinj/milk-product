package vn.shortsoft.products.dto.convert;

import java.util.ArrayList;
import java.util.List;

import vn.shortsoft.products.builder.ItemPropertiesBuilder;
import vn.shortsoft.products.builder.concretebuilder.ItemPropertiesConcreteBuilder;
import vn.shortsoft.products.dto.ItemPropertiesDTO;
import vn.shortsoft.products.model.ItemProperties;

public class ItemPropertiesConvert {

    public static ItemProperties convertToItemProperties(ItemPropertiesDTO itemPropertiesDTO) {
        ItemPropertiesBuilder itemPropertiesBuilder = new ItemPropertiesConcreteBuilder();
        return itemPropertiesBuilder
                .setColor(itemPropertiesDTO.getColor())
                .setMadeIn(itemPropertiesDTO.getMadeIn())
                .setMaterial(itemPropertiesDTO.getMaterial())
                .setPrice(itemPropertiesDTO.getPrice())
                .setSize(itemPropertiesDTO.getSize())
                .build();
    }

    public static List<ItemProperties> convertMultiToItemProperties(List<ItemPropertiesDTO> listItemPropertiesDTO) {
        List<ItemProperties> listItemProperties = new ArrayList<>();
        try {
            for (int i = 0; i < listItemPropertiesDTO.size(); i++) {
                listItemProperties.add(convertToItemProperties(listItemPropertiesDTO.get(i)));
            }
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
        return listItemProperties;
    }

    public static ItemPropertiesDTO convertToItemPropertiesDTO(ItemProperties itemProperties) {
        return ItemPropertiesDTO.builder()
                .color(itemProperties.getColor())
                .madeIn(itemProperties.getMadeIn())
                .material(itemProperties.getMaterial())
                .price(itemProperties.getPrice())
                .size(itemProperties.getSize())
                .build();
    }

    public static List<ItemPropertiesDTO> convertMultiToItemPropertiesDTO(List<ItemProperties> listItemProperties) {
        List<ItemPropertiesDTO> listItemPropertiesDTO = new ArrayList<>();
        for (ItemProperties itemProperties : listItemProperties) {
            listItemPropertiesDTO.add(convertToItemPropertiesDTO(itemProperties));
        }
        return listItemPropertiesDTO;
    }

}
