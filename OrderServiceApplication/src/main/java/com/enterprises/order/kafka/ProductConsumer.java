package com.enterprises.order.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.enterprises.order.dto.ProductDto;

@Component
public class ProductConsumer {

	@KafkaListener(topics = "product-topic", groupId = "order-group")
	public void consume(ProductDto dto) {

		System.out.println("Received Product Event:");
		System.out.println("ID: " + dto.getId());
		System.out.println("Name: " + dto.getName());
		System.out.println("Price: " + dto.getPrice());
	}

}
