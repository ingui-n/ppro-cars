package com.example.pizzeria.controller;

import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.AdminPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final AdminPizzaService adminPizzaService;

    @Autowired
    public MenuController(AdminPizzaService adminPizzaService) {
        this.adminPizzaService = adminPizzaService;
    }

    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("pizzas", adminPizzaService.getPizzas());
        return "menu";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Pizza pizza = adminPizzaService.getPizzaById(id);
        if (pizza != null) {
            model.addAttribute("pizza", pizza);
            return "menu_detail";
        }
        return "redirect:/menu";
    }
}
