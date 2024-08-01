// package vn.shortsoft.products.dto.convert;

// import vn.shortsoft.products.dto.ProdSaleDto;
// import vn.shortsoft.products.model.ProdSale;
// import vn.shortsoft.products.model.Product;

// public class ProdSaleConvert {
//     public static ProdSale convertToProdSale(ProdSaleDto prodSaleDto) {
//         return ProdSale.builder()
//                 .userId(prodSaleDto.getUserId())
//                 .orderId(prodSaleDto.getOrderId())
//                 .product(Product.builder().id(prodSaleDto.getProductDto().getId()).build())
//                 .build();
//     }

//     public static ProdSaleDto convertToProdSaleDto(ProdSale prodSale) {
//         return ProdSaleDto.builder()
//                 .userId(prodSale.getUserId())
//                 .orderId(prodSale.getOrderId())
//                 .build();
//     }
// }
