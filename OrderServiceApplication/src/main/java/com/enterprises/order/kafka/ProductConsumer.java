package com.enterprises.order.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.enterprises.order.dto.ProductDto;

@Component
public class ProductConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(ProductConsumer.class);

    @KafkaListener(topics = "product-topic-v2", groupId = "order-group-v2")
    public void consume(ProductDto dto) {

        log.info("Received Product Event:");
        log.info("ID: {}", dto.getId());
        log.info("Name: {}", dto.getName());
        log.info("Price: {}", dto.getPrice());
    }
}