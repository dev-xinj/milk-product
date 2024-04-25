package vn.shortsoft.products.builder.concretebuilder;

import java.util.List;

import vn.shortsoft.products.builder.ItemProductBuilder;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.model.User;
import vn.shortsoft.products.utils.IntegerUtils;

public class ItemProductConcreteBuilder implements ItemProductBuilder {
    private Long userId;
    private String itemName;
    private String itemCode;
    private String type;
    private String sku;
    private String status;
    private Integer totalNumber;
    private Integer purchaseNumber;
    private Integer seeNumber;
    private Integer likeNumber;
    private String description;
    private User user;
    private List<ItemProperties> listItemProperties;

    @Override
    public ItemProduct build() {
        ItemProduct itemProduct = new ItemProduct(itemName, itemCode, userId, totalNumber, purchaseNumber, seeNumber,
                likeNumber, description, type, sku, listItemProperties);
        if (status != null) {
            itemProduct.setStatus(status);
        }
        if (user != null) {
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

    @Override
    public ItemProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ItemProductBuilder setLikeNumber(Integer likeNumber) {
        if (IntegerUtils.hasValue(likeNumber)) {
            this.likeNumber = likeNumber;
        } else {
            this.likeNumber = 0;
        }
        return this;
    }

    @Override
    public ItemProductBuilder setPurchaseNumber(Integer purchaseNumber) {
        if (IntegerUtils.hasValue(purchaseNumber)) {
            this.purchaseNumber = purchaseNumber;
        } else {
            this.purchaseNumber = 0;
        }
        return this;
    }

    @Override
    public ItemProductBuilder setSeeNumber(Integer seeNumber) {
        if (IntegerUtils.hasValue(seeNumber)) {
            this.seeNumber = seeNumber;
        } else {
            this.seeNumber = 0;
        }
        return this;
    }

    @Override
    public ItemProductBuilder setTotalNumber(Integer totalNumber) {
        if (IntegerUtils.hasValue(totalNumber)) {
            this.totalNumber = totalNumber;
        } else {
            this.totalNumber = 0;
        }
        return this;
    }

}
