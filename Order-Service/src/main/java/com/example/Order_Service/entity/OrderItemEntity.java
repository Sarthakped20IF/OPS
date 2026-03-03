package com.example.Order_Service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
    private Double subTotal;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;
}
