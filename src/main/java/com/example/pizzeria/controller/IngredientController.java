package com.example.pizzeria.controller;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.MenuListing;
import com.example.pizzeria.service.IngredientService;
import com.example.pizzeria.service.MenuService;
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

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping({"", "/"})
    public String list(Model model) {
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "ingredients";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            return "ingredient_detail";
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
        return "ingredient_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("edit", true);
            return "ingredient_edit";
        }
        return "redirect:/admin/ingredients";
    }

    @PostMapping("/save")
    public String save(@Valid Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("ingredient", ingredientService.getIngredients());
            return "ingredient_edit";
        }
        ingredientService.saveIngredient(ingredient);
        return "redirect:/admin/ingredients";
    }
}
