package com.example.Product_Service.dtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
}
