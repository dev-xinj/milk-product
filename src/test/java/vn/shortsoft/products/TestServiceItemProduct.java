package vn.shortsoft.products;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Data;
import vn.shortsoft.products.builder.ItemProductBuilder;
import vn.shortsoft.products.builder.ItemPropertiesBuilder;
import vn.shortsoft.products.builder.concretebuilder.ItemProductConcreteBuilder;
import vn.shortsoft.products.builder.concretebuilder.ItemPropertiesConcreteBuilder;
import vn.shortsoft.products.model.CategoryConfig;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.model.User;
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

        User user = new User();
        CategoryConfig categoryConfig2 = new CategoryConfig("Quan Ao", "outFit", 2);

        @Test
        @Rollback(false)
        public void saveItemProductTests() {
                user.setUserName("admin");
                user.setFullName("Nguyen Van A");
                user.setId(10l);

                ItemPropertiesBuilder itemPropertiesBuilder = new ItemPropertiesConcreteBuilder();

                ItemProperties itemProperties1 = itemPropertiesBuilder.setColor("Vàng")
                                .setMadeIn("VietName")
                                .setMaterial("Inox")
                                .setPrice(135555f)
                                .setSize("130cm")
                                // .setCategoryConfig(categoryConfig1)
                                .build();
                ItemProperties itemProperties2 = itemPropertiesBuilder.setColor("Vàng")
                                .setMadeIn("JaPan")
                                .setMaterial("Sat")
                                .setPrice(155f)
                                .setSize("560cm")
                                .setCategoryConfig(categoryConfig2)
                                .build();


                ItemProductBuilder itemProductBuilder = new ItemProductConcreteBuilder();
                ItemProduct itemProduct = itemProductBuilder.setItemCode("IPHONE")
                                .setItemName("Iphone")
                                .setSku("SKU-IP")
                                .setType("DT")
                                .setUserId(2L)
                                .setUser(user)
                                .setStatus("ACTIVE")
                                .build();

                itemProduct.addItemProperties(itemProperties1);
                itemProduct.addItemProperties(itemProperties2);
                itemProductService.save(itemProduct);
                Assertions.assertThat(itemProduct.getId()).isGreaterThan(0);
        }

        @Test
        @Rollback(false)
        public void updateItemProductTests() {
                CategoryConfig categoryConfig1 = categoryConfigRepository.findById(4L).get();
                itemProductService.updateByStatus(11l, "SUSPEND");
        }

        @Test
        @Rollback(false)
        public void deleteByIdTest() {
                itemProductService.deleteById(6l);
        }
}
