package com.hsl.wechatpay.service.impl;
import com.hsl.wechatpay.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  17:08
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategorySericeImplTest {

    @Autowired
    private ProductCategorySericeImpl productCategorySerice;

    @Test
    public void findOne() {
        ProductCategory one = productCategorySerice.findOne(1);
        System.out.println(one);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = productCategorySerice.findAll();
        Assert.assertEquals(4,all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> byCategoryTypeIn = productCategorySerice.findByCategoryTypeIn(Arrays.asList(1, 2));
        System.out.println(byCategoryTypeIn.size());
    }

    @Test
    public void save() {
        productCategorySerice.save(new ProductCategory("kfc",2));
    }
}
