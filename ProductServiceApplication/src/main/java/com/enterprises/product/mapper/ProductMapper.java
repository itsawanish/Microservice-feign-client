package com.enterprises.product.mapper;

import com.enterprises.product.entity.Product;
import com.enterprises.product.dto.ProductDto;

public class ProductMapper {

    public static Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}