package com.hsl.wechatpay.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  20:20
 * desc:<商品product>
 */
@Data
@Entity
public class ProductInfo {
    @Id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品描述
    private String productDescription;

    //商品小图
    private String productIcon;

    //商品状态 0正常 1下架
    private Integer productStatus;

    //类目编号
    private Integer categoryType;
}
