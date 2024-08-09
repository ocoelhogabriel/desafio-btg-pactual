package com.gctech.btgpactual.ordems.controller.dto;

import com.gctech.btgpactual.ordems.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId, Long customerId, BigDecimal total) {


    public static OrderResponse fromEntity(OrderEntity entity){
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getOrderValue());
    }
}
