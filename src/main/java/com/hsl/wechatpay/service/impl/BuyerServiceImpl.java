package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.dto.OrderDTO;
import com.hsl.wechatpay.enums.ResultEnum;
import com.hsl.wechatpay.exception.SellException;
import com.hsl.wechatpay.service.BuyerService;
import com.hsl.wechatpay.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hsl on 2020-05-05
 * Time:星期二  22:41
 * desc:<一句话简述功能>
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO == null){
            log.error("[取消订单]订单不存在!,orderId={}",orderId);
            throw new SellException(ResultEnum.DETAIL_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO one = orderService.findOne(orderId);
        if(one == null){
            return null;
        }
        if(!one.getBuyerOpenId().equalsIgnoreCase(openid)){
            log.error("订单openid不一致,orderId={}",orderId);
            throw new SellException(ResultEnum.OPENID_ERROR);
        }
        return one;
    }
}
