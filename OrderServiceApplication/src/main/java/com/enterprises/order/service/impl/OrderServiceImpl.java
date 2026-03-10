package com.enterprises.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class OrderServiceImpl implements OrderService {

    private static final Logger log =
            LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository repository;

    @Autowired
    ProductFeignClient feignClient;

    @Override
    public OrderDto createOrder(OrderDto dto) {

        log.info("Creating order for productId: {}", dto.getProductId());

        ProductDto product =
                feignClient.getProduct(dto.getProductId());

        if (product == null) {
            log.error("Product not found for id: {}", dto.getProductId());
            throw new RuntimeException("Product not found");
        }

        Double total =
                product.getPrice() * dto.getQuantity();

        log.info("Calculated total price: {}", total);

        dto.setTotalPrice(total);

        Order order = OrderMapper.toEntity(dto);

        Order saved = repository.save(order);

        log.info("Order saved successfully with id: {}", saved.getId());

        return OrderMapper.toDto(saved);
    }

    @Override
    public OrderDto getOrderById(Long id) {

        log.info("Fetching order with id: {}", id);

        Order order = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with id: {}", id);
                    return new RuntimeException("Order not found");
                });

        return OrderMapper.toDto(order);
    }
}