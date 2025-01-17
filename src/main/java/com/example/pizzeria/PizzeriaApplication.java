package com.example.pizzeria;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.service.AdminPizzaService;
import com.example.pizzeria.service.AdminPizzaServiceImpl;
import com.example.pizzeria.service.IngredientService;
import com.example.pizzeria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PizzeriaApplication {

    private final UserService userService;
    private final IngredientService ingredientService;
    private final AdminPizzaService adminPizzaService;

    @Autowired
    public PizzeriaApplication(UserService userService, IngredientService ingredientService, AdminPizzaService adminPizzaService) {
        this.userService = userService;
        this.ingredientService = ingredientService;
        this.adminPizzaService = adminPizzaService;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            userService.addUser("admin",
                    "heslo",
                    "heslo",
                    "ADMIN",
                    "John",
                    "Adminovan",
                    "tin@ten.com",
                    "741258963"
            );
            userService.addUser("user",
                    "heslo",
                    "heslo",
                    "USER",
                    "Jane",
                    "Doe",
                    "lek@ten.com",
                    "641258961"
            );
            ingredientService.addIngredient("Marinara sauce", true);
            ingredientService.addIngredient("Pesto sauce", true);
            ingredientService.addIngredient("White garlic pizza sauce", true);
            ingredientService.addIngredient("BBQ sauce", false);
            ingredientService.addIngredient("Olive oil and garlic", true);
            ingredientService.addIngredient("Mozzarella", true);
            ingredientService.addIngredient("Burrata", true);
            ingredientService.addIngredient("Mexican cheese blend", true);
            ingredientService.addIngredient("Cheddar", false);
            ingredientService.addIngredient("Pepperoni", true);
            ingredientService.addIngredient("Sausage", true);
            ingredientService.addIngredient("Bacon", true);
            ingredientService.addIngredient("Anchovies", true);
            ingredientService.addIngredient("Chicken", true);
            ingredientService.addIngredient("Mushrooms", true);
            ingredientService.addIngredient("Ananas", false);
            ingredientService.addIngredient("Tomatoes", true);
            adminPizzaService.addPizza("Margherita", "Basic Margherita", 10.0, new ArrayList<>(List.of(
                    ingredientService.getIngredientById(17),
                    ingredientService.getIngredientById(4),
                    ingredientService.getIngredientById(1)
            )));
            adminPizzaService.addPizza("Pepperoni", "Basic Pepperoni", 9.9, new ArrayList<>(List.of(
                    ingredientService.getIngredientById(8),
                    ingredientService.getIngredientById(12),
                    ingredientService.getIngredientById(5)
            )));
            adminPizzaService.addPizza("Hawaiian", "Basic Hawaiian", 5.9, new ArrayList<>(List.of(
                    ingredientService.getIngredientById(16)
            )));
            adminPizzaService.addPizza("Supreme", "Basic Supreme", 8.9, new ArrayList<>(List.of(
                    ingredientService.getIngredientById(12)
            )));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }
}
