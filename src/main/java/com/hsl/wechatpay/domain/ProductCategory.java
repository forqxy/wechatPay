package com.hsl.wechatpay.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  15:57
 * desc:<类目>
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {

    //类目id
    @Id
    @GeneratedValue
    private Integer categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
