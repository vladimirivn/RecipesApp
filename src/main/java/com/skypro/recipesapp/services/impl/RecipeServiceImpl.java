package com.skypro.recipesapp.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.recipesapp.exception.ValidationException;
import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.FileService;
import com.skypro.recipesapp.services.RecipeService;
import com.skypro.recipesapp.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RecipeServiceImpl implements RecipeService {

    private final FileService fileService;
    private static long id = 1;
    private Map<Long, Recipe> recipes = new HashMap<>();
    private final ValidationService validationService;

    private final FileServiceImpl filesService;

    @Value("${path.to.data.files}")
    private String dataFilePath;
    @Value("${name.of.recipe.data.file}")
    private String dataFileName;
    @Value("${name.of.recipes.txt.file}")
    private String dataFileNameTxt;

    private Path recipePath;

    @PostConstruct
    private void init() {
        recipePath = Path.of(dataFilePath, dataFileName);
        recipes = filesService.readFromFile(recipePath, new TypeReference<>() {
        });
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipes.put(id++, recipe);
        filesService.saveToFile(recipes, recipePath);
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
        filesService.saveToFile(recipes, recipePath);
        return recipe;
    }

    @Override
    public Recipe deleteRecipeById(long id) {
        if (!recipes.containsKey(id)) {
            throw new ValidationException(recipes.toString());
        }
        Recipe recipe = recipes.remove(id);
        filesService.saveToFile(recipes, recipePath);
        return recipe;
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipes;
    }

    @Override
    public File readFile() {
        return recipePath.toFile();
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        fileService.uploadFile(file, recipePath);
        recipes = fileService.readFromFile(recipePath, new TypeReference<>() {
        });
    }

    @Override
    public File prepareRecipesToTxt() throws IOException {
        return filesService
                .saveTxtFile(getAllRecipeToString(), Path.of(dataFilePath, dataFileNameTxt))
                .toFile();
    }

    public String getAllRecipeToString() {
        StringBuilder stringBuilder = new StringBuilder();

        String str1 = " • ";

        for (Recipe recipe : recipes.values()) {
            stringBuilder.append("\n").append(recipe.toString()).append("\n");
            stringBuilder.append("\nИнгредиенты: \n");

            for (Ingredient ingredient : recipe.getIngredients()) {
                stringBuilder.append(str1).append(ingredient.toString()).append("\n");
            }
            stringBuilder.append("\nИнструкция приготовления:\n");
            int i = 0;
            for (String steps : recipe.getCookingInstructions()) {
                stringBuilder.append(" ").append(++i).append(" ").append(steps).append("\n");
            }
        }
        return stringBuilder.append("\n").toString();
    }

}

