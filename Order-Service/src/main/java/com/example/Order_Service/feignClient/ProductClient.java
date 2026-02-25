package com.example.Order_Service.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service",url = "http://localhost:8081")
public interface ProductClient {
    
}
