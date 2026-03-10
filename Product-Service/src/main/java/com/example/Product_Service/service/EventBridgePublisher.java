package com.example.Product_Service.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;

@Service
public class EventBridgePublisher {

    private final EventBridgeClient eventBridgeClient;

    public EventBridgePublisher(EventBridgeClient eventBridgeClient) {
        this.eventBridgeClient = eventBridgeClient;
    }

    public void publishEvent(String message) {

        PutEventsRequestEntry event = PutEventsRequestEntry.builder()
                .source("product-service")
                .detailType("ProductCreated")
                .detail("{\"message\":\"" + message + "\"}")
                .eventBusName("default")
                .build();

        PutEventsRequest request = PutEventsRequest.builder()
                .entries(event)
                .build();

        eventBridgeClient.putEvents(request);

        System.out.println("Event sent to EventBridge: " + message);
    }
}