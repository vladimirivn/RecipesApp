package com.skypro.recipesapp.services.impl;

import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null
                && recipe.getTitle() != null
                && !StringUtils.isEmpty(recipe.getTitle())
                && recipe.getCookingInstructions() != null
                && recipe.getCookingTime() >= 0
                && recipe.getIngredients() != null
                && !recipe.getIngredients().isEmpty()
                && !recipe.getCookingInstructions().isEmpty();
    }
    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && ingredient.getProductName()!= null
                && ingredient.getCount() >= 0
                && !StringUtils.isEmpty(ingredient.getProductName())
                && !StringUtils.isEmpty(ingredient.getUnit());
    }
}
