package com.skypro.recipesapp.model;

import java.util.Objects;

public class Ingredient {

    private String productName;
    private int count;
    private String unit;

    public Ingredient(String productName, int count, String unit) {
        this.productName = productName;
        this.count = count;
        this.unit = unit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return count == that.count && Objects.equals(productName, that.productName) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, count, unit);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "productName='" + productName + '\'' +
                ", count=" + count +
                ", unit='" + unit + '\'' +
                '}';
    }
}
