package vn.shortsoft.products.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shortsoft.products.dao.ProdSaleDao;
import vn.shortsoft.products.dto.ProdSaleDto;
import vn.shortsoft.products.dto.convert.ProdSaleConvert;
import vn.shortsoft.products.model.ProdSale;
import vn.shortsoft.products.services.ProdSaleService;

@Service
public class ProdSaleServiceImpl implements ProdSaleService {

    @Autowired
    ProdSaleDao prodSaleDao;

    @Override
    public Long saveSale(ProdSaleDto prodSaleDto) {
        ProdSale prodSale = ProdSaleConvert.convertToProdSale(prodSaleDto);
        return prodSaleDao.saveSale(prodSale);
    }

    @Override
    public int totalSaleByProductId(Long productId) {
        return prodSaleDao.totalSaleByProductId(productId);
    }
}
