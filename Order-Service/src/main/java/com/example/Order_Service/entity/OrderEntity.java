package com.example.Order_Service.entity;

import jakarta.persistence.*;
import lombok.Data;
//OrderEntity
//- id
//- productId
//- quantity
//- totalAmount
//- status
@Entity
@Data
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Object orderId;
    
    private Long productId;
    private Integer quantity;
    private Double Totalamt;
    private String status;

}
