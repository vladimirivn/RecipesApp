package com.skypro.recipesapp.services.impl;

import com.skypro.recipesapp.exception.ValidationException;
import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.services.IngredientService;
import com.skypro.recipesapp.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static long id = 1;
    private final Map<Long, Ingredient> ingredients = new HashMap<>();
    private final ValidationService validationService;

    public IngredientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Ingredient addNewIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredients.put(id++, ingredient);
        return ingredient;

    }

    @Override
    public Optional<Ingredient> getIngredientById(long id) {
        return Optional.ofNullable(ingredients.get(id));
    }

    @Override
    public Ingredient editIngredientById(long id, Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredients.replace(id, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient deleteIngredientById(long id) {
        if (!ingredients.containsKey(id)) {
            throw new ValidationException(ingredients.toString());
        }
        return ingredients.remove(id);
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredients;
    }


}
