package com.hsl.wechatpay.converter;

import com.hsl.wechatpay.domain.OrderMaster;
import com.hsl.wechatpay.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hsl on 2020-05-03
 * Time:星期日  15:07
 * desc:<一句话简述功能>
 */
public class OrderMaster2OrderDtoConverter {
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map( e -> convert(e))
                .collect(Collectors.toList());
    }
}
