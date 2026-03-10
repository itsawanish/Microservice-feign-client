package com.enterprises.product.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprises.product.dto.ProductDto;
import com.enterprises.product.entity.Product;
import com.enterprises.product.exception.ResourceNotFoundException;
import com.enterprises.product.kafka.ProductProducer;
import com.enterprises.product.mapper.ProductMapper;
import com.enterprises.product.repository.ProductRepository;
import com.enterprises.product.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger log =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;
    private final ProductProducer producer;

    public ProductServiceImpl(ProductRepository repository,
                              ProductProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {

        log.info("Creating product: {}", dto.getName());

        Product product = ProductMapper.toEntity(dto);

        Product savedProduct = repository.save(product);

        log.info("Product saved successfully with id: {}", savedProduct.getId());

        ProductDto response = ProductMapper.toDto(savedProduct);

        log.info("Sending product event to Kafka for id: {}", response.getId());

        producer.sendProductEvent(response);

        return response;
    }

    @Override
    public ProductDto getProductById(Long id) {

        log.info("Fetching product with id: {}", id);

        Product product = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    return new ResourceNotFoundException("Product not found with id " + id);
                });

        return ProductMapper.toDto(product);
    }
}