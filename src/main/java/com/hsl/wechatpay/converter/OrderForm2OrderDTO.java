package com.hsl.wechatpay.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hsl.wechatpay.domain.OrderDetail;
import com.hsl.wechatpay.dto.OrderDTO;
import com.hsl.wechatpay.enums.ResultEnum;
import com.hsl.wechatpay.exception.SellException;
import com.hsl.wechatpay.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsl on 2020-05-05
 * Time:星期二  21:04
 * desc:<一句话简述功能>
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenid());
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("json装换成list异常,string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
