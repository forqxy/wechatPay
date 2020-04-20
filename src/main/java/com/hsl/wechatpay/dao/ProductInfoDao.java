package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  20:28
 * desc:<一句话简述功能>
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer status);
}
