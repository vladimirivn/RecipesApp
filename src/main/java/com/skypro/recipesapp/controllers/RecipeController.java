package com.skypro.recipesapp.controllers;

import com.skypro.recipesapp.model.Recipe;
import com.skypro.recipesapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
