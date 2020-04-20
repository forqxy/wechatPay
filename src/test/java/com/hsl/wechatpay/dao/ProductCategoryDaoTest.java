package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  16:14
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOne(){
        ProductCategory one = productCategoryDao.findOne(1);
        System.out.println(one);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("清明节假");
        productCategory.setCategoryType(1);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void findByCategoryType(){
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        System.out.println(byCategoryTypeIn);

    }

}