package com.example.Order_Service.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {

    @NotNull(message = "ProductId must not be blank ")
    private Long productId;
    @NotNull(message = "Quantity must not be Null")
    @Min(value = 1 , message = "Quantity must be grater than 1")
    private Integer quantity;
}
