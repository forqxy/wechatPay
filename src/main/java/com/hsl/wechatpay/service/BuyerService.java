package com.hsl.wechatpay.service;

import com.hsl.wechatpay.dto.OrderDTO;

/**
 * Created by hsl on 2020-05-05
 * Time:星期二  22:34
 * desc:<买家service验证openid>
 */
public interface BuyerService {

    //查询单个订单
    OrderDTO findOneOrder(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
