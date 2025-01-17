package com.example.pizzeria.service;

import com.example.pizzeria.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {

    List<Ingredient> getIngredients();

    Ingredient getIngredientById(long id);

    void deleteIngredientById(long id);

    void saveIngredient(Ingredient ingredient);

    void addIngredient(String name, boolean available);
}
