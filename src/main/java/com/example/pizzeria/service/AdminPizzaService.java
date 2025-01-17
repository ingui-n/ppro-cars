package com.example.pizzeria.service;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPizzaService {

    List<Pizza> getPizzas();

    Pizza getPizzaById(long id);

    void deletePizzaById(long id);

    void savePizza(Pizza pizza);

    void addPizza(String name, String description, double price, List<Ingredient> ingredients);
}
