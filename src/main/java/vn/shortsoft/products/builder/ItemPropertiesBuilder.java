package vn.shortsoft.products.builder;

import vn.shortsoft.products.builder.concretebuilder.ItemPropertiesConcreteBuilder;
import vn.shortsoft.products.model.CategoryConfig;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.model.User;

public interface ItemPropertiesBuilder {
    // private String madeIn;
    // private Float price;
    // private String size;
    // private String color;
    // private String material;
    // private CategoryConfig categoryConfig;
    // private ItemProduct itemProduct;

    ItemPropertiesBuilder setMadeIn(String madeIn);
    ItemPropertiesBuilder setPrice(Float price);
    ItemPropertiesBuilder setSize(String size);
    ItemPropertiesBuilder setColor(String color);
    ItemPropertiesBuilder setMaterial(String material);
    ItemPropertiesBuilder setStatus(String status);
    ItemPropertiesBuilder setUser(User user);
    ItemPropertiesBuilder setCategoryConfig(CategoryConfig categoryConfig);
    ItemPropertiesBuilder setItemProduct(ItemProduct itemProduct);
    ItemProperties build();
}
