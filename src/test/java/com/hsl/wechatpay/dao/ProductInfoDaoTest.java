package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  20:31
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345");
        productInfo.setProductName("kfc");
        productInfo.setProductPrice(new BigDecimal(59));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("111");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        productInfoDao.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        Assert.assertEquals(2,productInfoList.size());
    }
}