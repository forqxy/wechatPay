package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.dao.ProductInfoDao;
import com.hsl.wechatpay.domain.ProductInfo;
import com.hsl.wechatpay.dto.CartDto;
import com.hsl.wechatpay.enums.ProductStatusEnum;
import com.hsl.wechatpay.enums.ResultEnum;
import com.hsl.wechatpay.exception.SellException;
import com.hsl.wechatpay.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoDao.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoDao.findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }
}
