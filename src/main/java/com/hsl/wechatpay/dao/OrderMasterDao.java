package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  19:34
 * desc:<一句话简述功能>
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    //分页按照买家的openid查询订单
    Page<OrderMaster> findByBuyerOpenId(String openId, Pageable pageable);


}
