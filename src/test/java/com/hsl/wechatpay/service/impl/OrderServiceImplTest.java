package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.domain.OrderDetail;
import com.hsl.wechatpay.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hsl on 2020-04-23
 * Time:星期四  22:20
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    private final String buyerOpenid = "111111";
    private final String orderId = "1587653270523136095";

    @Test
    public void createOrder() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("jerry");
        orderDTO.setBuyerAddress("南京市玄武区011号");
        orderDTO.setBuyerPhone("12345678920");
        orderDTO.setBuyerOpenId(buyerOpenid);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("12345");
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);
        orderServiceImpl.createOrder(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderServiceImpl.findOne(orderId);
        log.info("订单详情为{}",orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> list = orderServiceImpl.findList(buyerOpenid, pageRequest);
        log.info("订单列表为-------{}",list);
    }

    @Test
    public void cancelOrder() {
        OrderDTO orderDTO = orderServiceImpl.findOne(orderId);
        orderServiceImpl.cancelOrder(orderDTO);
    }

    @Test
    public void finishOrder() {
        OrderDTO orderDTO = orderServiceImpl.findOne(orderId);
        orderServiceImpl.finishOrder(orderDTO);
    }

    @Test
    public void payOrder() {
        OrderDTO orderDTO = orderServiceImpl.findOne(orderId);
        orderServiceImpl.payOrder(orderDTO);
    }
}