package com.skypro.recipesapp.controllers;

import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")

public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping()
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addNewRecipe(recipe));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable long id) {
        return ResponseEntity.of(recipeService.getRecipeById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipeById(@PathVariable long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.editRecipeById(id, recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteIngredientById(@PathVariable long id) {
        return ResponseEntity.ok(recipeService.deleteRecipeById(id));
    }
    @GetMapping
    public ResponseEntity<Map<Long,Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
}
