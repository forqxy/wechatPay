package com.hsl.wechatpay.service;

import com.hsl.wechatpay.domain.ProductCategory;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  16:57
 * desc:<一句话简述功能>
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory save(ProductCategory productCategory);
}
