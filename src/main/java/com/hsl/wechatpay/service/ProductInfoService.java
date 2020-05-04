package com.hsl.wechatpay.service;

import com.hsl.wechatpay.domain.ProductInfo;
import com.hsl.wechatpay.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  21:14
 * desc:<一句话简述功能>
 */
public interface ProductInfoService {

    //根据商品id查询单个商品
    ProductInfo findOne(String productId);

    //查询所有在架商品列表
    List<ProductInfo> findProductOnSell();

    //查询所有商品
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加减库存
    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);
}
