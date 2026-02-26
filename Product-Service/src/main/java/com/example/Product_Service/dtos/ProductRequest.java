package com.example.Product_Service.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product Name should not be Blank")
    private String name;
    @NotNull(message = "Price is required")
    @Positive(message = "Price Cannot be Negative")
    private Double price;
    @NotNull(message = "Stock is required")
    @Min(value = 0,message = "Stock Cannot be negative")
    private Integer stock;
    @NotBlank(message = "Product must have Category")
    private String category;
}
