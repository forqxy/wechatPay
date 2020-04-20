package com.hsl.wechatpay.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  22:11
 * desc:<商品包装类>
 */
@Data
public class ProductVo {

    //类目名称
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
