package com.example.Product_Service.config;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class OrderProcess {
    @ServiceActivator(inputChannel = "outputChannel")
    public void orderProcess(String message){
        System.out.println("Order Process "+message);
    }
}
