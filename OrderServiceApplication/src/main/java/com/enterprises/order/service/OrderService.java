package com.enterprises.order.service;

import com.enterprises.order.dto.OrderDto;

public interface OrderService {
	OrderDto createOrder(OrderDto dto);
    OrderDto getOrderById(Long id);

}
