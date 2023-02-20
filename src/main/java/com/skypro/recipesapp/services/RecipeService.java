package com.skypro.recipesapp.services;

import com.skypro.recipesapp.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Recipe addNewRecipe(Recipe recipe);

    Optional<Recipe> getRecipeById(long id);

    Recipe editRecipeById(long id, Recipe recipe);

    Recipe deleteRecipeById(long id);

    Map<Long, Recipe> getAllRecipes();

    File readFile();

    void uploadFile(MultipartFile file) throws IOException;

}
