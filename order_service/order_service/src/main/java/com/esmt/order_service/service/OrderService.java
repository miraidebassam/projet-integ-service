package com.esmt.order_service.service;

import com.esmt.order_service.models.Order;
import com.esmt.order_service.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

//    public List<Order> getAllOrdes() {
//        // Utiliser le nom logique du service ("user-service") dans l'URL
//        return restTemplate.getForObject("http://USER-SERVICE-CLIENT/api/users/all", List.class);
//    }


    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order existingOrder = getOrderById(id);
        orderRepository.delete(existingOrder);
    }
}
