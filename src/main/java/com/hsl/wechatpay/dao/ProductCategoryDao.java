package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  16:11
 * desc:<类目dao>
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
