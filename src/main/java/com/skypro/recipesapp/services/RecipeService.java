package com.skypro.recipesapp.services;

import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.model.Recipe;

import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Recipe addNewRecipe(Recipe recipe);

    Optional<Recipe> getRecipeById(long id);

    Recipe editRecipeById(long id, Recipe recipe);

    Recipe deleteRecipeById(long id);

    Map<Long, Recipe> getAllRecipes();
}
