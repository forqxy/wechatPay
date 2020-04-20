package com.hsl.wechatpay.vo;

import lombok.Data;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  22:04
 * desc:<http请求返回的封装对象>
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;
}
