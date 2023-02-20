package com.skypro.recipesapp.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.skypro.recipesapp.exception.ValidationException;
import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.services.FileService;
import com.skypro.recipesapp.services.IngredientService;
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

public class IngredientServiceImpl implements IngredientService {

    private final FileService fileService;
    private static long id = 1;
    private Map<Long, Ingredient> ingredients = new HashMap<>();
    private final ValidationService validationService;
    private final FileServiceImpl filesService;

    @Value("${path.to.data.files}")
    private String dataFilePath;
    @Value("${name.of.ingredient.data.file}")
    private String dataFileName;

    private Path ingredientPath;

    @PostConstruct
    private void init() {
        ingredientPath = Path.of(dataFilePath, dataFileName);
        ingredients = filesService.readFromFile(ingredientPath, new TypeReference<>() {
        });
    }

    @Override
    public Ingredient addNewIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredients.put(id++, ingredient);
        filesService.saveToFile(ingredients, ingredientPath);
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
        filesService.saveToFile(ingredients, ingredientPath);
        return ingredient;
    }

    @Override
    public Ingredient deleteIngredientById(long id) {
        if (!ingredients.containsKey(id)) {
            throw new ValidationException(ingredients.toString());
        }
        Ingredient ingredient = ingredients.remove(id);
        filesService.saveToFile(ingredients, ingredientPath);
        return ingredient;
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredients;
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        fileService.uploadFile(file, ingredientPath);
        ingredients = filesService.readFromFile(ingredientPath, new TypeReference<>() {
        });
    }


}
