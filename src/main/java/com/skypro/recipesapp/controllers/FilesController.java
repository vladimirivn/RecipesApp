package com.skypro.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/files")
@Tag(name = "Импорт/Экспорт фалов", description = "Импорт/Экспорт рецептов и ингредиентов")

public class FilesController {

    @GetMapping("/recipe/download")
    @Operation(summary = "Выгрузка файла рецептов")

    public ResponseEntity<InputStreamResource>

}
