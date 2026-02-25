package com.enterprises.order.service.impl;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprises.order.dto.OrderDto;
import com.enterprises.order.dto.ProductDto;
import com.enterprises.order.entity.Order;
import com.enterprises.order.feign.ProductFeignClient;
import com.enterprises.order.mapper.OrderMapper;
import com.enterprises.order.repository.OrderRepository;
import com.enterprises.order.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepository repository;
	@Autowired
	ProductFeignClient feignClient;
	
	
	 @Override
	    public OrderDto createOrder(OrderDto dto) {

	        ProductDto product =
	                feignClient.getProduct(dto.getProductId());

	        if (product == null) {
	            throw new ResourceNotFoundException("Product not found");
	        }

	        Double total =
	                product.getPrice() * dto.getQuantity();

	        dto.setTotalPrice(total);

	        Order order = OrderMapper.toEntity(dto);

	        Order saved = repository.save(order);

	        return OrderMapper.toDto(saved);
	    }

	    @Override
	    public OrderDto getOrderById(Long id) {

	        Order order = repository.findById(id)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Order not found"));

	        return OrderMapper.toDto(order);
	    }
	
	

}
