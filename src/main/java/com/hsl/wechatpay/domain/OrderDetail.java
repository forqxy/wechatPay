package com.hsl.wechatpay.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  17:52
 * desc:<订单详情>
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail {
    @Id
    private String detailId;

    //订单id
    private String orderId;

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //数量
    private Integer productQuantity;

    //商品图片
    private String productIcon;

    private Date createTime;

    private Date updateTime;
}
