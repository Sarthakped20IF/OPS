package com.example.Order_Service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID orderId;
    private Long productId;
    private Integer quantity;
    private Double Totalamt;
    private String status;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItemEntity> Items;
}
