package com.example.Product_Service.controller;

import com.example.Product_Service.dtos.ProductRequest;
import com.example.Product_Service.dtos.ProductResponse;
import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.ProductNotFound;
import com.example.Product_Service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products") public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) throws ProductNotFound {
        return productService.getProductById(id);
    }

}
