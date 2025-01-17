package com.example.pizzeria.controller;

import com.example.pizzeria.model.*;
import com.example.pizzeria.service.AdminPizzaService;
import com.example.pizzeria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private final AdminPizzaService adminPizzaService;
    private final OrderService orderService;

    @ModelAttribute("cart")
    public ShoppingCart createCart() {
        return new ShoppingCart();
    }

    @Autowired
    public CartController(AdminPizzaService adminPizzaService, OrderService orderService) {
        this.adminPizzaService = adminPizzaService;
        this.orderService = orderService;
    }

    @GetMapping({"/", ""})
    public String listOrders(Model model, @ModelAttribute("cart") ShoppingCart cart) {
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart/view";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long pizzaId, @RequestParam int quantity, @ModelAttribute("cart") ShoppingCart cart, Model model) {
        Pizza pizza = adminPizzaService.getPizzaById(pizzaId);

        if (pizza != null) {
            cart.addItem(pizza, quantity);
        }

        model.addAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long pizzaId, @ModelAttribute("cart") ShoppingCart cart) {
        cart.removeItemById(pizzaId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Model model, @ModelAttribute("cart") ShoppingCart cart) {
        if (cart.getItems().isEmpty()) {
            return "redirect:/cart?empty";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart/checkout";
    }

    @PostMapping("/order-placed")
    public String orderPlaced(@ModelAttribute("cart") ShoppingCart cart) {
        orderService.placeOrder(cart);
        cart.clear();
        return "cart/order-placed";
    }
}
