package com.example.kiem_tra_gk_di_dong.service;

import com.example.kiem_tra_gk_di_dong.dto.OrderDTO;
import com.example.kiem_tra_gk_di_dong.dto.OrderItemDTO;
import com.example.kiem_tra_gk_di_dong.entity.Order;
import com.example.kiem_tra_gk_di_dong.entity.OrderItem;
import com.example.kiem_tra_gk_di_dong.entity.OrderStatus;
import com.example.kiem_tra_gk_di_dong.entity.Product;
import com.example.kiem_tra_gk_di_dong.repository.OrderRepository;
import com.example.kiem_tra_gk_di_dong.repository.ProductRepository;
import com.example.kiem_tra_gk_di_dong.dto.OrderItemDTO;
import com.example.kiem_tra_gk_di_dong.entity.Order;
import com.example.kiem_tra_gk_di_dong.entity.OrderItem;
import com.example.kiem_tra_gk_di_dong.entity.OrderStatus;
import com.example.kiem_tra_gk_di_dong.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import com.example.kiem_tra_gk_di_dong.repository.OrderRepository;
import com.example.kiem_tra_gk_di_dong.repository.ProductRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
    
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerPhone(orderDTO.getCustomerPhone());
        order.setCustomerAddress(orderDTO.getCustomerAddress());
        order.setStatus(OrderStatus.PENDING);
        
        double totalAmount = 0;
        
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDTO.getProductId()));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(product.getPrice());
            
            order.getItems().add(orderItem);
            totalAmount += orderItem.getSubTotal();
        }
        
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }
    
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        order.setStatus(status);
        return orderRepository.save(order);
    }
    
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

