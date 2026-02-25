package com.enterprises.product.service;

import com.enterprises.product.dto.ProductDto;

public interface ProductService {
	
	ProductDto createProduct(ProductDto dto);

    ProductDto getProductById(Long id);

}
