package com.enterprises.order.mapper;

import com.enterprises.order.dto.OrderDto;
import com.enterprises.order.entity.Order;

public class OrderMapper {

	public static Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setProductId(dto.getProductId());
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        return order;
    }

    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setProductId(order.getProductId());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }
	
}
