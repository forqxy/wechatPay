package com.hsl.wechatpay.exception;

import com.hsl.wechatpay.enums.ResultEnum;

/**
 * Created by hsl on 2020-04-20
 * Time:星期一  22:18
 * desc:<自定义异常>
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
