package com.example.Order_Service.dtos;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long productId;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
//  Product Details
    private String ProductName;
    private String Category;
    private Integer Currentstock;
}
