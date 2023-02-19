package com.skypro.recipesapp.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.recipesapp.exception.ValidationException;
import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.RecipeService;
import com.skypro.recipesapp.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RecipeServiceImpl implements RecipeService {

    private static long id = 1;
    private Map<Long, Recipe> recipes = new HashMap<>();
    private final ValidationService validationService;

    private final FileService filesService;

    @Value("${path.to.data.files}")
    private String dataFilePath;
    @Value("${name.of.recipe.data.file}")
    private String dataFileName;

    private Path recipePath;

    @PostConstruct
    private void init() {
        recipePath = Path.of(dataFilePath,dataFileName);
        recipes = filesService.readDataFromFile(recipePath, new TypeReference<>() {});
    }
    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.put(id++, recipe);
        filesService.saveDataToFile(recipes, recipePath);
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
        filesService.saveDataToFile(recipes, recipePath);
        return recipe;
    }

    @Override
    public Recipe deleteRecipeById(long id) {
        if (!recipes.containsKey(id)) {
            throw new ValidationException(recipes.toString());
        }
        Recipe recipe = recipes.remove(id);
        filesService.saveDataToFile(recipes, recipePath);
        return recipe;
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipes;
    }

}
