// package vn.shortsoft.products.dao.impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import vn.shortsoft.products.dao.ProdSaleDao;
// import vn.shortsoft.products.model.ProdSale;
// import vn.shortsoft.products.repository.ProdSaleRepository;

// @Component
// public class ProdSaleDaoImpl implements ProdSaleDao {

//     @Autowired
//     ProdSaleRepository prodSaleRepository;

//     @Override
//     public Long saveSale(ProdSale prodSale) {
//         return prodSaleRepository.save(prodSale).getId();
//     }

//     @Override
//     public int totalSaleByProductId(Long productId) {
//         return prodSaleRepository.totalSaleByProductId(productId).intValue();
//     }
// }
