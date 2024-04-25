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
    // private int totalNumber;
    // private int purchaseNumber;
    // private int seeNumber;
    // private int likeNumber;
    ItemProductBuilder setUserId(Long userId);

    ItemProductBuilder setItemName(String itemName);

    ItemProductBuilder setItemCode(String itemCode);

    ItemProductBuilder setType(String type);

    ItemProductBuilder setSku(String sku);

    ItemProductBuilder setStatus(String status);

    ItemProductBuilder setTotalNumber(Integer totalNumber);

    ItemProductBuilder setPurchaseNumber(Integer purchaseNumber);

    ItemProductBuilder setSeeNumber(Integer seeNumber);

    ItemProductBuilder setLikeNumber(Integer likeNumber);

    ItemProductBuilder setDescription(String description);

    ItemProductBuilder setUser(User user);

    ItemProductBuilder setlistItemProperties(List<ItemProperties> listItemProperties);

    ItemProduct build();
}
