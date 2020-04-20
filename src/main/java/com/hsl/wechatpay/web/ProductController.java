package com.hsl.wechatpay.web;

import com.hsl.wechatpay.domain.ProductCategory;
import com.hsl.wechatpay.domain.ProductInfo;
import com.hsl.wechatpay.service.ProductCategoryService;
import com.hsl.wechatpay.service.ProductInfoService;
import com.hsl.wechatpay.util.ResultVoUtil;
import com.hsl.wechatpay.vo.ProductInfoVo;
import com.hsl.wechatpay.vo.ProductVo;
import com.hsl.wechatpay.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hsl on 2020-04-06
 * Time:星期一  22:01
 * desc:<一句话简述功能>
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/list")
    public ResultVo list(){

        //查询所有的上架商品
        List<ProductInfo> productOnSellList = productInfoService.findProductOnSell();
        //查询类目
        List<Integer> categoryTypeList = productOnSellList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //拼装数据
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory : byCategoryTypeIn){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productOnSellList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        ResultVo resultVo = ResultVoUtil.success(productVoList);
        return resultVo;
    }
}
