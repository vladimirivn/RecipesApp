package com.skypro.recipesapp.model;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Ingredient {

    private String productName;
    private int count;
    private String unit;

    @Override
    public String toString() {
        return productName + " - " + count + " " + unit;
    }

}
