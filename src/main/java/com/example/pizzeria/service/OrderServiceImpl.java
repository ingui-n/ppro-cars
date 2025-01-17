package com.example.pizzeria.service;

import com.example.pizzeria.model.CartItem;
import com.example.pizzeria.model.Order;
import com.example.pizzeria.model.ShoppingCart;
import com.example.pizzeria.repository.OrderRepository;
import com.example.pizzeria.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrderById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
        }
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void placeOrder(ShoppingCart cart) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = new Order();
        order.setUser(userDetails.getUser());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("Received");
        order.setPizzas(cart.getItems().stream()
                .flatMap(cartItem -> java.util.stream.IntStream.range(0, cartItem.getQuantity())
                        .mapToObj(i -> cartItem.getPizza()))
                .toList());
        order.setPrice(cart.getTotalPrice());

        saveOrder(order);
    }
}
