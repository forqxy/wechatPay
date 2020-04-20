package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.dto.OrderDTO;
import com.hsl.wechatpay.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hsl on 2020-04-12
 * Time:星期日  22:57
 * desc:<一句话简述功能>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        //1.查询订单数量,价格

        //2.计算总价

        //3.入库

        //4.减库存
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        return null;
    }
}
