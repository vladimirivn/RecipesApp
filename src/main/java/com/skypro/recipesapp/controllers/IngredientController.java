package com.skypro.recipesapp.controllers;

import com.skypro.recipesapp.model.Ingredient;
import com.skypro.recipesapp.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD операции по работе с ингредиентами")

public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping()
    @Operation(summary = "Добавление нового ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ингредиент добавлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка добавления ингредиента")
    })

    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addNewIngredient(ingredient));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингредиента", description = "Получение ингредиента по его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ингредиент получен"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден")
    })
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.getIngredientById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение ингредиента", description = "Изменение ингредиента по его id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ингредиент изменен"),
            @ApiResponse(responseCode = "400", description = "Ошибка изменения ингредиента")
    })
    public ResponseEntity<Ingredient> editIngredientById(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.editIngredientById(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента", description = "Удаление ингредиента по его id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ингредиент удален"),
            @ApiResponse(responseCode = "400", description = "Ошибка удаления ингредиента")
    })

    public ResponseEntity<Ingredient> deleteIngredientById(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.deleteIngredientById(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка всех ингредиентов")
    @ApiResponse(responseCode = "200", description = "Список ингредиентов получен")

    public ResponseEntity<Map<Long, Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}
