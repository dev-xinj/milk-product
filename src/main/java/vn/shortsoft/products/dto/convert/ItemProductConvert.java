package vn.shortsoft.products.dto.convert;

import vn.shortsoft.products.builder.ItemProductBuilder;
import vn.shortsoft.products.builder.concretebuilder.ItemProductConcreteBuilder;
import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.model.ItemProduct;

public class ItemProductConvert {

    public static ItemProduct convertToItemProduct(ItemProductDTO itemProductDTO) {
        ItemProductBuilder itemProductBuilder = new ItemProductConcreteBuilder();
        return itemProductBuilder.setItemCode(itemProductDTO.getItemCode())
                .setItemName(itemProductDTO.getItemName())
                .setSku(itemProductDTO.getSku())
                .setType(itemProductDTO.getType())
                .setUserId(itemProductDTO.getUserId())
                .setlistItemProperties(
                        ItemPropertiesConvert.convertMultiToItemProperties(itemProductDTO.getListItemPropertiesDto()))
                .build();

    }

    public static ItemProductDTO convertToItemProductDTO(ItemProduct itemProduct) {
        return ItemProductDTO.builder()
                .sku(itemProduct.getSku())
                .type(itemProduct.getType())
                .itemCode(itemProduct.getItemCode())
                .itemName(itemProduct.getItemName())
                .listItemPropertiesDto(
                        ItemPropertiesConvert.convertMultiToItemPropertiesDTO(itemProduct.getListItemProperties()))
                .build();
    }
}
