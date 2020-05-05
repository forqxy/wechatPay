package com.hsl.wechatpay.service.impl;

import com.hsl.wechatpay.converter.OrderMaster2OrderDtoConverter;
import com.hsl.wechatpay.dao.OrderDetailDao;
import com.hsl.wechatpay.dao.OrderMasterDao;
import com.hsl.wechatpay.domain.OrderDetail;
import com.hsl.wechatpay.domain.OrderMaster;
import com.hsl.wechatpay.domain.ProductInfo;
import com.hsl.wechatpay.dto.CartDto;
import com.hsl.wechatpay.dto.OrderDTO;
import com.hsl.wechatpay.enums.OrderStatusEnum;
import com.hsl.wechatpay.enums.ProductStatusEnum;
import com.hsl.wechatpay.enums.ResultEnum;
import com.hsl.wechatpay.exception.SellException;
import com.hsl.wechatpay.service.OrderService;
import com.hsl.wechatpay.service.ProductInfoService;
import com.hsl.wechatpay.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hsl on 2020-04-12
 * Time:星期日  22:57
 * desc:<一句话简述功能>
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.generKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询订单数量,价格
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.generKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
            //入库orderDetail
            orderDetailDao.save(orderDetail);
        }

        //3.入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        orderMasterDao.save(orderMaster);

        //4.减库存
        List<CartDto> cartDtoList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.DETAIL_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> byBuyerOpenId = orderMasterDao.findByBuyerOpenId(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDtoConverter.convert(byBuyerOpenId.getContent());
        Page<OrderDTO> page = new PageImpl<OrderDTO>(orderDTOList,pageable,byBuyerOpenId.getTotalElements());
        return page;
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            log.error("订单取消异常,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult == null){
            log.error("订单状态更新失败,orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("取消的订单无商品详情,orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_ERROR);
        }
        List<CartDto> cartDtoList = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);
        //退款
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getStatus())){
            //todo
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            log.error("订单状态不正确,orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getStatus());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster save = orderMasterDao.save(orderMaster);
        if(save == null){
            log.error("订单完结异常,orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            log.error("订单状态不正确,已支付orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //支付状态
        if(!orderDTO.getPayStatus().equals(OrderStatusEnum.WAIT.getStatus())){
            log.error("订单支付状态异常.orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_PAY_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(OrderStatusEnum.SUCCESS.getStatus());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster save = orderMasterDao.save(orderMaster);
        if(save == null){
            log.error("订单完结异常,orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }
}
