package vn.shortsoft.products.builder.concretebuilder;

import vn.shortsoft.products.builder.ItemPropertiesBuilder;
import vn.shortsoft.products.model.CategoryConfig;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.model.User;

public class ItemPropertiesConcreteBuilder implements ItemPropertiesBuilder {
    private String madeIn;
    private Float price;
    private String size;
    private String color;
    private String material;
    private String status;
    private User user;
    private CategoryConfig categoryConfig;
    private ItemProduct itemProduct;

    @Override
    public ItemProperties build() {
        ItemProperties itemProperties = new ItemProperties(madeIn, price, size, color, material, categoryConfig,
                itemProduct);
        if (status != null) {
            itemProperties.setStatus(status);
        }
        if (user != null) {
            itemProperties.setUser(user);
        }
        return itemProperties;
    }

    @Override
    public ItemPropertiesBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setMadeIn(String madeIn) {
        this.madeIn = madeIn;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setMaterial(String material) {
        this.material = material;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setPrice(Float price) {
        this.price = price;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setCategoryConfig(CategoryConfig categoryConfig) {
        this.categoryConfig = categoryConfig;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setItemProduct(ItemProduct itemProduct) {
        this.itemProduct = itemProduct;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public ItemPropertiesBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

}
