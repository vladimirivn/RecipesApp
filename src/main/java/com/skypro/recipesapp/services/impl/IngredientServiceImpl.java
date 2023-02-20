package com.skypro.recipesapp.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.recipesapp.exception.ValidationException;
import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.services.IngredientService;
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

public class IngredientServiceImpl implements IngredientService {

    private static long id = 1;
    private Map<Long, Ingredient> ingredients = new HashMap<>();
    private final ValidationService validationService;
    private final FileService filesService;

    @Value("${path.to.data.files}")
    private String dataFilePath;
    @Value("${name.of.ingredient.data.file}")
    private String dataFileName;

    private Path ingredientPath;

    @PostConstruct
    private void init() {
        ingredientPath = Path.of(dataFilePath, dataFileName);
        ingredients = filesService.readDataFromFile(ingredientPath, new TypeReference<>() {
        });
    }

    @Override
    public Ingredient addNewIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredients.put(id++, ingredient);
        filesService.saveDataToFile(ingredients, ingredientPath);
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
        filesService.saveDataToFile(ingredients, ingredientPath);
        return ingredient;
    }

    @Override
    public Ingredient deleteIngredientById(long id) {
        if (!ingredients.containsKey(id)) {
            throw new ValidationException(ingredients.toString());
        }
        Ingredient ingredient = ingredients.remove(id);
        filesService.saveDataToFile(ingredients, ingredientPath);
        return ingredient;
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredients;
    }


}
