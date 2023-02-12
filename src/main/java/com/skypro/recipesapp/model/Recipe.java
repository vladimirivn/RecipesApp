package com.skypro.recipesapp.model;

import java.util.List;
import java.util.Objects;

public class Recipe {

    private String title;
    private int cookingTime;

    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;

    public Recipe(String title, int cookingTime, List<Ingredient> ingredients, List<String> cookingInstructions) {
        this.title = title;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(List<String> cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return cookingTime == recipe.cookingTime && Objects.equals(title, recipe.title) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(cookingInstructions, recipe.cookingInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cookingTime, ingredients, cookingInstructions);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", cookingTime=" + cookingTime +
                ", ingredients=" + ingredients +
                ", cookingInstructions=" + cookingInstructions +
                '}';
    }
}
