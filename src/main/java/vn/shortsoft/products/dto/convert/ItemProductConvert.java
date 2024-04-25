package vn.shortsoft.products.dto.convert;

import vn.shortsoft.products.builder.ItemProductBuilder;
import vn.shortsoft.products.builder.ItemPropertiesBuilder;
import vn.shortsoft.products.builder.concretebuilder.ItemProductConcreteBuilder;
import vn.shortsoft.products.builder.concretebuilder.ItemPropertiesConcreteBuilder;
import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.model.ItemProduct;

public class ItemProductConvert {

        public static ItemProduct convertToItemProduct(ItemProductDTO itemProductDTO) {
                ItemProductBuilder itemProductBuilder = new ItemProductConcreteBuilder();
                ItemPropertiesBuilder itemPropertiesBuilder = new ItemPropertiesConcreteBuilder();
                ItemProduct itemProduct = itemProductBuilder.setItemCode(itemProductDTO.getItemCode())
                                .setItemName(itemProductDTO.getItemName())
                                .setSku(itemProductDTO.getSku())
                                .setType(itemProductDTO.getType())
                                .setUserId(itemProductDTO.getUserId())
                                .setUser(itemProductDTO.getUser())
                                .setDescription(itemProductDTO.getDescription())
                                .setLikeNumber(itemProductDTO.getLikeNumber())
                                .setPurchaseNumber(itemProductDTO.getPurchaseNumber())
                                .setSeeNumber(itemProductDTO.getSeeNumber())
                                .build();
                itemProductDTO.getListItemPropertiesDto()
                                .forEach(a -> itemProduct.addItemProperties(itemPropertiesBuilder.setColor(a.getColor())
                                                .setMadeIn(a.getMadeIn())
                                                .setMaterial(a.getMadeIn())
                                                .setPrice(a.getPrice())
                                                .setSize(a.getSize())
                                                .setTotalNumber(a.getTotalNumber())
                                                .build()));
                return itemProduct;

        }

        public static ItemProductDTO convertToItemProductDTO(ItemProduct itemProduct) {
                return ItemProductDTO.builder()
                                .sku(itemProduct.getSku())
                                .type(itemProduct.getType())
                                .itemCode(itemProduct.getItemCode())
                                .itemName(itemProduct.getItemName())
                                .likeNumber(itemProduct.getLikeNumber())
                                .purchaseNumber(itemProduct.getPurchaseNumber())
                                .totalNumber(itemProduct.getTotalNumber())
                                .description(itemProduct.getDescription())
                                .seeNumber(itemProduct.getSeeNumber())
                                .listItemPropertiesDto(
                                                ItemPropertiesConvert.convertMultiToItemPropertiesDTO(
                                                                itemProduct.getListItemProperties()))
                                .build();
        }
}
