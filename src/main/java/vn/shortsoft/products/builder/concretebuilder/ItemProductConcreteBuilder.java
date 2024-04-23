package vn.shortsoft.products.builder.concretebuilder;

import java.util.List;

import vn.shortsoft.products.builder.ItemProductBuilder;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.model.User;

public class ItemProductConcreteBuilder implements ItemProductBuilder {
    private Long userId;
    private String itemName;
    private String itemCode;
    private String type;
    private String sku;
    private String status;
    private User user;
    private List<ItemProperties> listItemProperties;

    @Override
    public ItemProduct build() {
        ItemProduct itemProduct = new ItemProduct(itemName, itemCode, userId, type, sku, listItemProperties);
        if(status != null){
            itemProduct.setStatus(status);
        }
        if(user != null){
            itemProduct.setUser(user);
        }
        return itemProduct;
    }

    @Override
    public ItemProductBuilder setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    @Override
    public ItemProductBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    @Override
    public ItemProductBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    @Override
    public ItemProductBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public ItemProductBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public ItemProductBuilder setlistItemProperties(List<ItemProperties> listItemProperties) {
        this.listItemProperties = listItemProperties;
        return this;
    }

    @Override
    public ItemProductBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public ItemProductBuilder setUser(User user) {
        this.user = user;
        return this;
    }


}
