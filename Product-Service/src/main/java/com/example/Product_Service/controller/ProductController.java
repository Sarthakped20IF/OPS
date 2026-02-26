package com.example.Product_Service.controller;

import com.example.Product_Service.dtos.ProductRequest;
import com.example.Product_Service.dtos.ProductResponse;
import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.ProductNotFound;
import com.example.Product_Service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    public List<ProductResponse> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/price-greater-than")
    public List<ProductResponse> getProductsByPrice(@RequestParam Double price){
        return productService.getProductWithPriceGreaterThan(price);
    }

    @GetMapping("/low-stock")
    public List<ProductResponse> getProductByStock(@RequestParam Integer stock){
        return productService.getLowStockProduct(stock);
    }

    @GetMapping("/product-page") public Page<ProductEntity> getProducts(Pageable pageable){
        return productService.getPagination(pageable);
    }

}
