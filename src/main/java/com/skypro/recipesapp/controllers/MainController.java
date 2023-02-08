package com.skypro.recipesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping
    public String index() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "Автор: Владимир Иваненко</br>"+
                "Название проекта: RecipesApp</br>"+
                "Дата создания проекта: 06.02.2023</br>" +
                "Описание проекта: Сайт рецептов";
    }
}
