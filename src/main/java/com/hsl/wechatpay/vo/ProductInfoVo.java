package com.hsl.wechatpay.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  22:16
 * desc:<一句话简述功能>
 */
@Data
public class ProductInfoVo {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDesc;

    @JsonProperty("icon")
    private String productIcon;
}
