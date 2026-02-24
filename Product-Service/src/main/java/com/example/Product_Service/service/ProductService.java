package com.example.Product_Service.service;

import com.example.Product_Service.dtos.ProductRequest;
import com.example.Product_Service.dtos.ProductResponse;
import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.ProductNotFound;
import com.example.Product_Service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct( ProductRequest request){
        ProductEntity product = new ProductEntity();
        product.setProdName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        ProductEntity saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    public ProductResponse getProductById(Long id)  {
        ProductEntity product = productRepository.findById(id).orElseThrow(()->new ProductNotFound("Product Not Found with Id: "+id));
        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(ProductEntity product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getProdName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        return response;

    }
}
