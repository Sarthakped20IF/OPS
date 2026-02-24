package com.example.Product_Service.repository;

import com.example.Product_Service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long > {

}
