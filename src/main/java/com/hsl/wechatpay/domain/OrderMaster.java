package com.hsl.wechatpay.domain;

import com.hsl.wechatpay.enums.OrderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  17:36
 * desc:<订单主表>
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    @Column(name = "buyerOpenid")
    private String buyerOpenId;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getStatus();

    private Integer payStatus = OrderStatusEnum.WAIT.getStatus();

    private Date createTime;

    private Date updateTime;
}
