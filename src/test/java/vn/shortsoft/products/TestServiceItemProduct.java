package vn.shortsoft.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import vn.shortsoft.products.model.CategoryConfig;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.reponsitory.CategoryConfigRepository;
import vn.shortsoft.products.reponsitory.ItemProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestServiceItemProduct {
    @Autowired
    private ItemProductRepository itemProductService;

    @Autowired
    private CategoryConfigRepository categoryConfigRepository;
    @Test
    @Rollback(false)
    public void saveItemProductTests(){
        List<ItemProperties> listItemProperties = new ArrayList<ItemProperties>();
        CategoryConfig categoryConfig = new CategoryConfig();
        categoryConfig = categoryConfigRepository.findById(3L).get();
        ItemProduct itemProduct = new ItemProduct("Iphone 15", "IPHONE", "Phone", "SKU-Phone",listItemProperties);
        ItemProperties itemProperties1= new ItemProperties("Viet nam", 2000f, "S", "Vàng", "gỗ",categoryConfig,new ItemProduct());
        ItemProperties itemProperties2 = new ItemProperties("Viet nam", 2000f, "S", "Vàng", "gỗ",categoryConfig,new ItemProduct());
        itemProperties1.setItemProduct(itemProduct);
        itemProperties2.setItemProduct(itemProduct);
        listItemProperties.add(itemProperties1);
        listItemProperties.add(itemProperties2);
        itemProduct.setListItemProperties(listItemProperties);
        // categoryConfig.setListItemProperties(listItemProperties);
        // categoryConfigRepository.save(categoryConfig);
        itemProductService.save(itemProduct);
        Assertions.assertThat(itemProduct.getId()).isGreaterThan(0);
    }

}
