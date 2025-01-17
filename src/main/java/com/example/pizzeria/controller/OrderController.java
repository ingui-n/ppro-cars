package com.example.pizzeria.controller;

import com.example.pizzeria.model.CartItem;
import com.example.pizzeria.model.Order;
import com.example.pizzeria.model.ShoppingCart;
import com.example.pizzeria.model.User;
import com.example.pizzeria.service.AdminPizzaService;
import com.example.pizzeria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping({"/admin/order", "/admin/orders"})
public class OrderController {

    private final OrderService orderService;
    private final AdminPizzaService adminPizzaService;

    @Autowired
    public OrderController(OrderService orderService, AdminPizzaService adminPizzaService) {
        this.orderService = orderService;
        this.adminPizzaService = adminPizzaService;
    }

    @GetMapping({"/", ""})
    public String listOrders(Model model) {
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "admin/order/list";
    }

    @GetMapping("/detail/{id}")
    public String viewOrder(Model model, @PathVariable long id) {
        Order order = orderService.getOrderById(id);

        if (order == null) {
            return "redirect:/orders";
        }

        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    @PostMapping("/detail/{id}/update-status")
    public String updateStatus(Model model, @PathVariable Long id, @RequestParam String status) {
        Order order = orderService.getOrderById(id);

        if (order != null) {
            order.setStatus(status);
            orderService.saveOrder(order);

            model.addAttribute("order", order);
            return "admin/order/detail";
        }

        return "redirect:/admin/orders";
    }
}
