package com.skypro.recipesapp.controllers;

import com.skypro.recipesapp.services.IngredientService;
import com.skypro.recipesapp.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@Tag(name = "Импорт/Экспорт файлов", description = "Импорт/Экспорт рецептов и ингредиентов")
@RequiredArgsConstructor

public class FilesController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/recipe/download")
    @Operation(summary = "Выгрузка файла рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Файл рецептов выгружен"),
            @ApiResponse(responseCode = "400", description = "Ошибка выгрузки файла рецептов")
    })

    public ResponseEntity<InputStreamResource> downloadDataFile() {
        try {
            File file = recipeService.readFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/recipe/download/txt")
    @Operation(summary = "Выгрузка рецептов в txt файл")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Файл рецептов выгружен"),
            @ApiResponse(responseCode = "400", description = "Ошибка выгрузки файла рецептов")
    })

    public ResponseEntity<InputStreamResource> downloadTxtFile() {
        try {
            File file = recipeService.prepareRecipesToTxt();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/recipe/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Файл рецептов загружен"),
            @ApiResponse(responseCode = "400", description = "Ошибка загрузки фала рецептов"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })

    public ResponseEntity<String> uploadDatafile(@RequestParam MultipartFile file) {
        try {
            recipeService.uploadFile(file);
            return ResponseEntity.ok("Файл успешно загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка загрузки файла");
        }
    }
    @PostMapping(value = "/ingredient/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла ингредиентов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Файл ингредиентов загружен"),
            @ApiResponse(responseCode = "400", description = "Ошибка загрузки файла ингредиентов"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })

    public ResponseEntity<String> uploadIngredient(@RequestParam MultipartFile file) {
        try {
            ingredientService.uploadFile(file);
            return ResponseEntity.ok("Файл успешно загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка загрузки файла");
        }
    }

}
