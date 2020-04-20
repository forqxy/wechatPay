package com.hsl.wechatpay.enums;

import lombok.Getter;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  21:21
 * desc:<商品状态枚举类>
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer status;
    private String msg;
    ProductStatusEnum(Integer status,String msg) {
        this.status = status;
        this.msg = msg;
    }
}
