package com.mycompany.Service;

import com.mycompany.Repository.OrderRepository;
import com.mycompany.Repository.ProductRepository;
import com.mycompany.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Order order) {
        order.getItems().forEach(item -> {
            productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setOrder(order);
        });
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    public Optional<Order> getOrderById(Long id) { return orderRepository.findById(id); }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setUserId(orderDetails.getUserId());
        order.getItems().clear();
        orderDetails.getItems().forEach(item -> {
            productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setOrder(order);
            order.getItems().add(item);
        });
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
