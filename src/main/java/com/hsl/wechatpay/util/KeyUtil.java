package com.hsl.wechatpay.util;

import java.util.Random;

/**
 * Created by hsl on 2020-04-20
 * Time:星期一  22:39
 * desc:<生成随机主键>
 */
public class KeyUtil {

    public static synchronized String generKey(){

        Random random = new Random();

        Integer num = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
