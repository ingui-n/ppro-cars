package com.example.pizzeria.service;

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
}
