package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.domain.ProductInfo;
import com.hsl.wechatpay.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  21:28
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123");
        Assert.assertEquals(new BigDecimal(59),productInfo.getProductPrice());
    }

    @Test
    public void findProductOnSell() {
        List<ProductInfo> productInfoList = productInfoService.findProductOnSell();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<ProductInfo> productInfos = productInfoService.findAll(pageRequest);
        System.out.println(productInfos.getTotalPages());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("mm");
        productInfo.setProductName("mmm");
        productInfo.setProductPrice(new BigDecimal(39));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("111");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getStatus());
        productInfo.setCategoryType(1);
        productInfoService.save(productInfo);
    }
}