package com.example.Product_Service.Integration;



import com.example.Product_Service.config.OrderGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class IntegrationTest {

    @Autowired
    private OrderGateway orderGateway;

    @Test
    void testMessageFlow() {

        String response = orderGateway.processOrder("order created");

        System.out.println("Response: " + response);

    }
}
