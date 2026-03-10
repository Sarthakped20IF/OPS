package com.example.Order_Service;

import com.example.Order_Service.dtos.OrderRequest;
import com.example.Order_Service.entity.OrderEntity;
import com.example.Order_Service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TestOrderServiceimpl {

    @InjectMocks
    private OrderService orderService;
    @InjectMocks
    private OrderRequest repo;

// A test entity will be created before
    @BeforeEach
    void setUp(){
        OrderEntity testOrder = new OrderEntity();
        testOrder.setOrderId(UUID.randomUUID());
        testOrder.setProductId(2L);
        testOrder.setQuantity(10);
        testOrder.setTotalamt(1000.0);
        testOrder.setStatus("created");
    }
}
