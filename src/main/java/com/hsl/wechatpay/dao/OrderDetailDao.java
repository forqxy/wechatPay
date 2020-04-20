package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  19:39
 * desc:<一句话简述功能>
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
