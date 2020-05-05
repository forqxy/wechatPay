package com.hsl.wechatpay.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hsl on 2020-05-04
 * Time:星期一  15:38
 * desc:<一句话简述功能>
 */
@Data
public class OrderForm {

    @NotEmpty(message = "请先填写姓名")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}
