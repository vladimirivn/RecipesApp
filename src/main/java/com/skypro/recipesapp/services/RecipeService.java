package com.skypro.recipesapp.services;

import com.skypro.recipesapp.model.Recipe;

import java.util.Optional;

public interface RecipeService {

    Recipe addNewRecipe(Recipe recipe);

    Optional<Recipe> getRecipeById(long id);


}
