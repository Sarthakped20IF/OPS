package com.example.Order_Service.service;

import com.example.Order_Service.dtos.*;
import com.example.Order_Service.entity.OrderEntity;
import com.example.Order_Service.entity.OrderItemEntity;
import com.example.Order_Service.feignClient.ProductClient;
import com.example.Order_Service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final ProductClient client;
    private final OrderRepository orderRepository;

    public OrderService(ProductClient client, OrderRepository orderRepository) {
        this.client = client;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        OrderEntity order = new OrderEntity();
        order.setStatus("CREATED");
        List<OrderItemEntity> orderItems = new ArrayList<>();
        double amt =0;
        for (OrderItemRequest itemreq : request.getItems()){
            ProductResponse  product = client.getProductById(itemreq.getProductId());
            if (product.getStock()< itemreq.getQuantity()){
                throw new RuntimeException("Insufficient stock");
            }
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(itemreq.getQuantity());
            orderItem.setPrice(product.getPrice());
            double subtotal = product.getPrice()*itemreq.getQuantity();
            orderItem.setSubTotal(subtotal);
//            itemreq.setOrder(order);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            amt += subtotal;
        }
        order.setItems(orderItems);
        order.setTotalamt(amt);
        orderRepository.save(order);
        return maptoOrderResponseWithProduct(order);

    }


    public OrderResponse getOrderById(UUID orderId){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not Found with id:"));
        return maptoOrderResponseWithProduct(order);
    }

    private OrderResponse maptoOrderResponseWithProduct (OrderEntity order){
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setTotalAmount(order.getTotalamt());
//        response.setProductId(order.getProductId());
        response.setOrderStatus(order.getStatus());

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> {
                    OrderItemResponse orderItem = new OrderItemResponse();
                    orderItem.setProductId(item.getProductId());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());
                    orderItem.setTotalPrice(item.getPrice()*item.getQuantity());
                    ProductResponse product = client.getProductById(item.getProductId());
                    orderItem.setProductName(product.getName());
                    orderItem.setCategory(product.getCategory());
                    orderItem.setCurrentstock(product.getStock());
                    return orderItem;
                })
                .toList();
        response.setItems(items);
        return response;
    }
}
