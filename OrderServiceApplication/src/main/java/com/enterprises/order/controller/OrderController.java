package com.enterprises.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprises.order.dto.OrderDto;
import com.enterprises.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired	
	OrderService service;
	
	 @PostMapping
	    public ResponseEntity<OrderDto> createOrder(
	            @Valid @RequestBody OrderDto dto) {

	        return ResponseEntity.ok(service.createOrder(dto));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<OrderDto> getOrder(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(service.getOrderById(id));
	    }
}
