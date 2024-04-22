package vn.shortsoft.products.builder;

import java.util.List;

import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.model.ItemProperties;
import vn.shortsoft.products.model.User;

public interface ItemProductBuilder {

    // private Long userId;
    // private String itemName;
    // private String itemCode;
    // private String type;
    // private String sku;
    // private List<ItemProperties> listItemProperties;

    ItemProductBuilder setUserId(Long userId);
    ItemProductBuilder setItemName(String itemName);
    ItemProductBuilder setItemCode(String itemCode);
    ItemProductBuilder setType(String type);
    ItemProductBuilder setSku(String sku);
    ItemProductBuilder setStatus(String status);
    ItemProductBuilder setUser(User user);
    ItemProductBuilder setlistItemProperties(List<ItemProperties> listItemProperties);

    ItemProduct build();
}
