package com.skypro.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Информация о проекте", description = "Информация о проекте, ФИО автора, описание и дата создания")

public class MainController {

    @GetMapping
    @Operation(summary = "Запрос состояние приложения")

    public String index() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    @Operation(summary = "Запрос информации о проекте")

    public String info() {
        return "Автор: Владимир Иваненко</br>"+
                "Название проекта: RecipesApp</br>"+
                "Дата создания проекта: 06.02.2023</br>" +
                "Описание проекта: Сайт рецептов";
    }
}
