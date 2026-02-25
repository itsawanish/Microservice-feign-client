package com.enterprises.product.kafka;

import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;
import com.enterprises.product.dto.ProductDto;

@Component
public class ProductProducer {
	
	private final KafkaTemplate<String, ProductDto> kafkaTemplate;

    public ProductProducer(KafkaTemplate<String, ProductDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductEvent(ProductDto dto) {

        kafkaTemplate.send("product-topic", dto);
    }
	
	
	

}
