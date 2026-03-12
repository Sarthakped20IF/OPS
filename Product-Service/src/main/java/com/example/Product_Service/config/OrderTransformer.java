package com.example.Product_Service.config;

import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class OrderTransformer {
    @Transformer(inputChannel = "inputChannel",outputChannel="outputChannel")
    public String transformer(String message) {
        return message.toUpperCase();
    }
}
