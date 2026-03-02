package com.example.Order_Service.service;

import com.example.Order_Service.dtos.OrderResponse;
import com.example.Order_Service.dtos.ProductResponse;
import com.example.Order_Service.feignClient.ProductClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OrderService {
    private final ProductClient client;

    public OrderService(ProductClient client) {
        this.client = client;
    }

    public void createOrder(Long prodId , Integer quantity){
        ProductResponse  product = client.getProductById(prodId);
        if (product.getStock()< quantity){
            throw new RuntimeException("Insufficient stock");
        }
        Double totalAmt = product.getPrice()*quantity;

        System.out.println("Order created. Total amt: " + totalAmt);
    }
    public OrderResponse create
}
