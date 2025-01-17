package com.example.pizzeria.service;

import com.example.pizzeria.model.Order;
import com.example.pizzeria.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getOrders();

    Order getOrderById(long id);

    void deleteOrderById(long id);

    void saveOrder(Order order);

    void placeOrder(ShoppingCart cart);
}
