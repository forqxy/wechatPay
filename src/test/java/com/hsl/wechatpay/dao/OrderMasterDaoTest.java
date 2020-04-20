package com.hsl.wechatpay.dao;

import com.hsl.wechatpay.domain.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  19:42
 * desc:<一句话简述功能>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("001");
        orderMaster.setBuyerName("hou");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerOpenId("001");
        orderMaster.setBuyerAddress("南京市玄武区");
        orderMaster.setOrderAmount(new BigDecimal(12));
        orderMasterDao.save(orderMaster);
    }
    @Test
    public void findByBuyerOpenId() {
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderMaster> byBuyerOpenId = orderMasterDao.findByBuyerOpenId("001", pageRequest);
        System.out.println(byBuyerOpenId.getTotalPages());
    }
}