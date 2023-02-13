package com.skypro.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class Ingredient {

    private String productName;
    private int count;
    private String unit;

}
