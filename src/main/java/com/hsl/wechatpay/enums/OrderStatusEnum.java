package com.hsl.wechatpay.enums;

import lombok.Getter;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  17:42
 * desc:<一句话简述功能>
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer status;
    private String msg;

    OrderStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
