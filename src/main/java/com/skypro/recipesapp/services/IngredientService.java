package com.skypro.recipesapp.services;

import com.skypro.recipesapp.model.Ingredient;

import java.util.Optional;

public interface IngredientService {

    Ingredient addNewIngredient(Ingredient ingredient);

    Optional<Ingredient> getIngredientById(long id);


}
