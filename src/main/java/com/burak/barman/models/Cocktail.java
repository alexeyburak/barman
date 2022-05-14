package com.burak.barman.models;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Cocktail {

    private int id;
    private String name;
    private String recipe;
    private String ingredients;
    private String img;


    public Cocktail(int id, String name, String recipe, String ingredients, String img) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
