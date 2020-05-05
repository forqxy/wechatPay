package com.hsl.wechatpay.enums;

import lombok.Getter;

/**
 * Created by hsl on 2020-04-20
 * Time:星期一  22:26
 * desc:<一句话简述功能>
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    DETAIL_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_ERROR(15,"订单状态更新失败"),
    ORDER_DETAIL_ERROR(16,"取消的订单无商品"),
    ORDER_PAY_ERROR(17,"订单支付状态不正常"),
    PARAM_ERROR(1,"创建订单参数不正确"),
    CART_EMPTY(18,"购物车不能为空"),
    OPENID_ERROR(19,"订单不属于当前用户")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
