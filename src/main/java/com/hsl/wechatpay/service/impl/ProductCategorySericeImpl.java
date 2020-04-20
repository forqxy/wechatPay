package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.dao.ProductCategoryDao;
import com.hsl.wechatpay.domain.ProductCategory;
import com.hsl.wechatpay.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  17:01
 * desc:<一句话简述功能>
 */
@Service
public class ProductCategorySericeImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return productCategoryDao.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }

}
