package com.hsl.wechatpay.dto;

import lombok.Data;

/**
 * Created by hsl on 2020-04-20
 * Time:星期一  22:53
 * desc:<一句话简述功能>
 */
@Data
public class CartDto {
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
