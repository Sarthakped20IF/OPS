package com.example.Order_Service.feignClient;

import com.example.Order_Service.dtos.ProductResponse;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:8081")
public interface ProductClient {
    @GetMapping("/api/products/{id}") ProductResponse getProductById(@PathVariable Long id);
}
