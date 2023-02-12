package com.skypro.recipesapp.controllers;

import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")

public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping()
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addNewIngredient(ingredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.getIngredientById(id));
    }
}
