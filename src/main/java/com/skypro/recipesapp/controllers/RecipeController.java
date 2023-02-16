package com.skypro.recipesapp.controllers;

import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD операции по работе с рецептами")

public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping()
    @Operation(summary = "Добавление нового рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка добавления рецепта")
    })
    @Parameters(value = {@Parameter(name = "Название", example = "Коктейль Отвертка")})

    public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addNewRecipe(recipe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение рецепта", description = "Получение рецепта по его id")
    @ApiResponse(responseCode = "200", description = "Рецепт получен")

    public ResponseEntity<Recipe> getRecipeById(@PathVariable long id) {
        return ResponseEntity.of(recipeService.getRecipeById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецепта", description = "Изменение рецепта по его id.")
    @ApiResponse(responseCode = "200", description = "Рецепт изменен")

    public ResponseEntity<Recipe> editRecipeById(@PathVariable long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.editRecipeById(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "Удаление рецепта по его id.")
    @ApiResponse(responseCode = "200", description = "Рецепт удален")

    public ResponseEntity<Recipe> deleteIngredientById(@PathVariable long id) {
        return ResponseEntity.ok(recipeService.deleteRecipeById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка всех рецептов")
    @ApiResponse(responseCode = "200", description = "Список рецептов получен")

    public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
}
