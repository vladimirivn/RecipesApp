package com.skypro.recipesapp.model;

import lombok.*;

import java.util.List;

//@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Recipe {

    private String title;
    private int cookingTime;

    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;

    @Override
    public String toString(){
        return title + "\n Время приготовления: " + cookingTime + " минут";
    }

}
