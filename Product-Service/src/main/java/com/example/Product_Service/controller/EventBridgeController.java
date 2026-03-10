package com.example.Product_Service.controller;

import com.example.Product_Service.service.EventBridgePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventBridgeController {

    private final EventBridgePublisher publisher;

    public EventBridgeController(EventBridgePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/publish")
    public String publish(@RequestParam String message) {

        publisher.publishEvent(message);

        return "Event published to EventBridge";
    }
}