package com.hsl.wechatpay.web;

import com.hsl.wechatpay.converter.OrderForm2OrderDTO;
import com.hsl.wechatpay.dto.OrderDTO;
import com.hsl.wechatpay.enums.ResultEnum;
import com.hsl.wechatpay.exception.SellException;
import com.hsl.wechatpay.form.OrderForm;
import com.hsl.wechatpay.service.BuyerService;
import com.hsl.wechatpay.service.OrderService;
import com.hsl.wechatpay.util.ResultVoUtil;
import com.hsl.wechatpay.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hsl on 2020-05-04
 * Time:星期一  15:34
 * desc:<一句话简述功能>
 */
@RestController
@Slf4j
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String,String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单参数不正确],orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO order = orderService.createOrder(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",order.getOrderId());
        return ResultVoUtil.success(map);

    }

    /**
     * 获取订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                                         @RequestParam(value = "size",defaultValue = "5")Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表]openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDTO> list = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(list.getContent());
    }

    /**
     * 获取订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVo<List<OrderDTO>> detail(@RequestParam("openid") String openid,
                                         @RequestParam("orderId")String orderId){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表]openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo<List<OrderDTO>> cancel(@RequestParam("openid") String openid,
                                           @RequestParam("orderId")String orderId){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表]openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

}
