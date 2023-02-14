package com.skypro.recipesapp.services.impl;

import com.skypro.recipesapp.exception.ValidationException;
import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.RecipeService;
import com.skypro.recipesapp.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static long id = 1;
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private final ValidationService validationService;

    public RecipeServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }


    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.put(id++, recipe);
        return recipe;
    }

    @Override
    public Optional<Recipe> getRecipeById(long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public Recipe editRecipeById(long id, Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.replace(id, recipe);
        return recipe;
    }

    @Override
    public Recipe deleteRecipeById(long id) {
        if (!recipes.containsKey(id)) {
            throw new ValidationException(recipes.toString());
//            return null;
        }
        return recipes.remove(id);
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipes;
    }

}
