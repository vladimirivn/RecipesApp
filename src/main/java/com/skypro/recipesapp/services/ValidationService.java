package com.skypro.recipesapp.services;

import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.model.Recipe;

public interface ValidationService {
    public boolean validate(Ingredient ingredient);

    public boolean validate(Recipe recipe);

}
