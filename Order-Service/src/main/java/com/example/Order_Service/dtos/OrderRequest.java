package com.example.Order_Service.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @Valid
    @NotEmpty(message = "List must contain Orders")
    private List<OrderItemRequest> items;
}
