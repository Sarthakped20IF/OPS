package com.example.Product_Service.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @SqsListener("product-queue")
    private void recieveMsg(String msg) {
        System.out.println("Recieved Message: "+msg);
    }
}
