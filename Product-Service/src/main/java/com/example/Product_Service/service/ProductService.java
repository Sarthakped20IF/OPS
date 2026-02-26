package com.example.Product_Service.service;

import com.example.Product_Service.dtos.ProductRequest;
import com.example.Product_Service.dtos.ProductResponse;
import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.ProductNotFound;
import com.example.Product_Service.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        product.setCategory(request.getCategory());
        ProductEntity saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    public ProductResponse getProductById(Long id)  {
        ProductEntity product = productRepository.findById(id).orElseThrow(()->new ProductNotFound("Product Not Found with Id: "+id));
        return mapToResponse(product);
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ProductResponse> getProductWithPriceGreaterThan(@Param("price") Double price){
        return productRepository.findProductWithPriceGreaterThan(price)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public List<ProductResponse> getLowStockProduct(@Param("stock") Integer stock){
        return productRepository.findLowStockProduct(stock)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Page<ProductEntity> getPagination(Pageable pageable){
        pageable = PageRequest.ofSize(5);
        return productRepository.findAll(pageable);
    }

    private ProductResponse mapToResponse(ProductEntity product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getProdName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCategory(product.getCategory());
        return response;

    }


}
