package com.hsl.wechatpay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsl.wechatpay.domain.OrderDetail;
import com.hsl.wechatpay.enums.OrderStatusEnum;
import com.hsl.wechatpay.util.Date2LongUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by hsl on 2020-04-12
 * Time:星期日  22:47
 * desc:<一句话简述功能>
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenId;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    @JsonSerialize(using = Date2LongUtil.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongUtil.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
