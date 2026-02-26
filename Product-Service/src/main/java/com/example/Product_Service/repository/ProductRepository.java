package com.example.Product_Service.repository;

import com.example.Product_Service.entity.ProductEntity;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long > ,
        JpaSpecificationExecutor<ProductEntity> {

    @QueryHints({
            @QueryHint(name = "org.hibernate.cacheable",value = "true")
    })
    @Query("SELECT p FROM ProductEntity p WHERE p.price > :price")
    List<ProductEntity> findProductWithPriceGreaterThan(@Param("price") Double price);


    @Query(value = "SELECT * FROM products WHERE stock < :stock",nativeQuery = true)
    List<ProductEntity>findLowStockProduct(@Param("stock") Integer stock);
}
