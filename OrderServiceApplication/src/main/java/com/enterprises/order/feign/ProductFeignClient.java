package com.enterprises.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.enterprises.order.dto.ProductDto;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
	
	 @GetMapping("/products/{id}")
	    ProductDto getProduct(@PathVariable("id") Long id);

}
