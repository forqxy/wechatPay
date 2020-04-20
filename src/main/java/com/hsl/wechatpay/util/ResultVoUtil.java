package com.hsl.wechatpay.util;

import com.hsl.wechatpay.vo.ResultVo;

/**
 * Created by hsl on 2020-04-11
 * Time:星期六  17:25
 * desc:<一句话简述功能>
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
