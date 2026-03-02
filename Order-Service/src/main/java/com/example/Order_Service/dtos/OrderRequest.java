package com.example.Order_Service.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {
    private Long productId;
    private Integer quantity;
}
