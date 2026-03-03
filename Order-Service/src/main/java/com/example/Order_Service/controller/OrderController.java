package com.example.Order_Service.controller;

import com.example.Order_Service.dtos.OrderRequest;
import com.example.Order_Service.dtos.OrderResponse;
import com.example.Order_Service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order-service")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/order")
    public OrderResponse createOrder( @Valid @RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }
}
