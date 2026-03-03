package com.example.Order_Service.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data public class OrderResponse {
    private UUID orderId;
    private Double totalAmount;
    private String orderStatus;
    private List<OrderItemResponse>items;
}
