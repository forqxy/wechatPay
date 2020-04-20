package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.dao.ProductInfoDao;
import com.hsl.wechatpay.domain.ProductInfo;
import com.hsl.wechatpay.enums.ProductStatusEnum;
import com.hsl.wechatpay.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  21:19
 * desc:<一句话简述功能>
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findProductOnSell() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getStatus());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }
}
