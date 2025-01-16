package com.example.pizzeria.controller;

import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.AdminPizzaService;
import com.example.pizzeria.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/pizza", "/admin/pizzas"})
public class PizzaController {

    private final AdminPizzaService adminPizzaService;
    private final IngredientService ingredientService;

    @Autowired
    public PizzaController(AdminPizzaService adminPizzaService, IngredientService ingredientService) {
        this.adminPizzaService = adminPizzaService;
        this.ingredientService = ingredientService;
    }

    @GetMapping({"/", ""})
    public String list(Model model) {
        model.addAttribute("pizzas", adminPizzaService.getPizzas());
        return "admin/pizza/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Pizza pizza = adminPizzaService.getPizzaById(id);
        if (pizza != null) {
            model.addAttribute("pizza", pizza);
            return "admin/pizza/detail";
        }
        return "redirect:/admin/pizza";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        adminPizzaService.deletePizzaById(id);
        return "redirect:/admin/pizza";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("edit", false);
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "admin/pizza/edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Pizza pizza = adminPizzaService.getPizzaById(id);
        if (pizza != null) {
            model.addAttribute("pizza", pizza);
            model.addAttribute("edit", true);
            model.addAttribute("ingredients", ingredientService.getIngredients());
            return "admin/pizza/edit";
        }
        return "redirect:/admin/pizza";
    }

    @PostMapping("/save")
    public String save(@Valid Pizza pizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("pizzas", adminPizzaService.getPizzas());
            model.addAttribute("ingredients", ingredientService.getIngredients());
            return "admin/pizza/edit";
        }
        adminPizzaService.savePizza(pizza);
        return "redirect:/admin/pizza";
    }
}
