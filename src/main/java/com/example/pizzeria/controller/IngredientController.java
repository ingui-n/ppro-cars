package com.example.pizzeria.controller;

import com.example.pizzeria.model.Ingredient;
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
@RequestMapping({"/admin/ingredient", "/admin/ingredients"})
public class IngredientController {

    private final IngredientService ingredientService;
    private final AdminPizzaService adminPizzaService;

    @Autowired
    public IngredientController(IngredientService ingredientService, AdminPizzaService adminPizzaService) {
        this.ingredientService = ingredientService;
        this.adminPizzaService = adminPizzaService;
    }

    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "admin/ingredient/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            return "admin/ingredient/detail";
        }
        return "redirect:/admin/ingredients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        ingredientService.deleteIngredientById(id);
        return "redirect:/admin/ingredients";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("edit", false);
        model.addAttribute("pizzas", adminPizzaService.getPizzas());
        return "admin/ingredient/edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("edit", true);
            model.addAttribute("pizzas", adminPizzaService.getPizzas());
            return "admin/ingredient/edit";
        }
        return "redirect:/admin/ingredients";
    }

    @PostMapping("/save")
    public String save(@Valid Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("ingredient", ingredientService.getIngredients());
            model.addAttribute("pizzas", adminPizzaService.getPizzas());
            return "admin/ingredient/edit";
        }
        ingredientService.saveIngredient(ingredient);
        return "redirect:/admin/ingredients";
    }
}
