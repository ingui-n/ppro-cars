package com.example.pizzeria.service;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPizzaServiceImpl implements AdminPizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public AdminPizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> getPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza getPizzaById(long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePizzaById(long id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            pizzaRepository.delete(pizza.get());
        }
    }

    @Override
    public void savePizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Override
    public void addPizza(String name, String description, double price, List<Ingredient> ingredients) {
        Pizza pizza = new Pizza();
        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setPrice(price);
        pizza.setIngredients(ingredients);
        pizzaRepository.save(pizza);
    }
}
