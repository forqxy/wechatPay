package com.hsl.wechatpay.service;

import com.hsl.wechatpay.dto.OrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hsl on 2020-04-12
 * Time:星期日  22:43
 * desc:<一句话简述功能>
 */
public interface OrderService {

    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询订单列表
    List<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);

    //完结订单
    OrderDTO finishOrder(OrderDTO orderDTO);

    //支付订单
    OrderDTO payOrder(OrderDTO orderDTO);
}
