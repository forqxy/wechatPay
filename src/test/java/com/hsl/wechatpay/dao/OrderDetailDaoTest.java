package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hsl on 2020-04-12
 * Time:星期日  22:35
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("001");
        orderDetail.setDetailId("001");
        orderDetail.setProductIcon("");
        orderDetail.setProductId("001");
        orderDetail.setProductName("kfc");
        orderDetail.setProductPrice(new BigDecimal(36));
        orderDetail.setProductQuantity(2);
        orderDetailDao.save(orderDetail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = orderDetailDao.findByOrderId("001");
        System.out.println(byOrderId);
    }
}