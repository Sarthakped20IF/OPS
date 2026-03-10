package com.example.Product_Service.service;

//import io.awspring.cloud.sqs.operations.SendOptions;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private final SqsTemplate sqsTemplate;

    public Producer(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void sendMessage(String msg){
        sqsTemplate.send("product-queue",msg);
    }
}
