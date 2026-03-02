package com.example.Product_Service.batch.processor;

import com.example.Product_Service.entity.ProductEntity;
import lombok.NonNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProductProcess implements ItemProcessor<ProductEntity,ProductEntity> {
    @Nullable
    @Override
    public ProductEntity process(@NonNull ProductEntity product) throws Exception {
        product.setPrice(product.getPrice()*0.5); // reducing the price by 5%
        return product;
    }
}
