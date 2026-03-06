package com.example.Product_Service.service;

import com.example.Product_Service.dtos.ProductRequest;
import com.example.Product_Service.dtos.ProductResponse;
import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.ProductNotFound;
import com.example.Product_Service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductserviceimplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;



    @BeforeEach
    void setUp(){
        Long productId = 1L;
        ProductEntity testEntity = new ProductEntity();
        testEntity.setId(productId);
        testEntity.setProdName("Testmobile");
        testEntity.setPrice(100.0);
        testEntity.setCategory("Electronics");
        testEntity.setStock(10);
    }
    @Test
    void TestgetById_(){
        Long productId = 2L;

        ProductEntity testEntity = new ProductEntity();
        testEntity.setId(productId);
        testEntity.setProdName("Testmobile");
        testEntity.setPrice(100.0);
        testEntity.setCategory("Electronics");
        testEntity.setStock(10);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(testEntity));

        ProductResponse response = productService.getProductById(productId);
        assertNotNull(response);
        assertEquals("Testmobile", response.getName());
        assertEquals(100.0, response.getPrice());
        assertEquals("Electronics", response.getCategory());
        assertEquals(10, response.getStock());

        verify(productRepository,times(1)).findById(productId);
    }

    @Test
    void TestgetById_ProductNotFound_(){
        Long productId = 10L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(productId);
        });

        assertEquals("Product Not Found ", exception.getMessage());
        verify(productRepository,times(1)).findById(productId);
    }

//    @Test
//    void TestcreateProduct(){
//        ProductResponse testResponse = new ProductResponse();
//        testResponse.setName("Testmobile");
//        testResponse.setPrice(100.0);
//        testResponse.setCategory("Electronics");
//        testResponse.setStock(10);
//        verify(productRepository,times(1)).save(any(ProductEntity.class));
//
//    }
    @Test
    void TestcreateProduct() {

        // Arrange
        ProductRequest request = new ProductRequest();
        request.setName("Testmobile");
        request.setPrice(100.0);
        request.setCategory("Electronics");
        request.setStock(10);

        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setProdName("Testmobile");
        entity.setPrice(100.0);
        entity.setCategory("Electronics");
        entity.setStock(10);

        when(productRepository.save(any(ProductEntity.class)))
                .thenReturn(entity);

        // Act
        ProductResponse response = productService.createProduct(request);

        // Assert
        assertNotNull(response);
        assertEquals("Testmobile", response.getName());

        verify(productRepository, times(1))
                .save(any(ProductEntity.class));
    }

    @Test
    void TestfindAll() {
        ProductEntity testEntity = new ProductEntity();
        testEntity.setId(1L);
        testEntity.setProdName("Testmobile");
        testEntity.setPrice(100.0);
        testEntity.setCategory("Electronics");
        testEntity.setStock(10);

        ProductEntity t2 = new ProductEntity();
        t2.setId(1L);
        t2.setProdName("Testlaptop");
        t2.setPrice(100.0);
        t2.setCategory("Electronics");
        t2.setStock(10);
        List<ProductEntity> testList = List.of(testEntity,t2);

        when(productRepository.findAll()).thenReturn(List.of(testEntity,t2));

        List<ProductResponse>response= productService.getAllProducts();
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Testmobile", response.get(0).getName());
        assertEquals("Testlaptop", response.get(1).getName());
        verify(productRepository,times(1)).findAll();

    }

    @Test
    void TestgetProductWithPriceGreaterthan(){
        Double price = 10000.0;
        ProductEntity t1 = new ProductEntity();
        t1.setId(1L);
        t1.setProdName("Testmobile");
        t1.setPrice(2000.0);
        t1.setCategory("Electronics");
        t1.setStock(10);
        ProductEntity t2 = new ProductEntity();
        t2.setId(11L);
        t2.setProdName("Testlaptop");
        t2.setPrice(20000.0);
        t2.setCategory("Electronics");
        t2.setStock(10);

        List<ProductEntity> testList = List.of(t1,t2);
        when(productRepository.findProductWithPriceGreaterThan(price)).thenReturn(List.of(t1,t2));

        List<ProductResponse>response= productService.getProductWithPriceGreaterThan(price);
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Testlaptop", response.get(1).getName());
        verify(productRepository,times(1)).findProductWithPriceGreaterThan(price);
    }

}
