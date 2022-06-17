package com.burak.barman.models;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Cocktail {

    private final int id;
    private String name;
    private final String recipe;
    private final String preparation;
    private final String recipe_amount;
    private final String img;

    public Cocktail(int id, String name, String recipe, String preparation, String recipe_amount, String img) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.preparation = preparation;
        this.recipe_amount = recipe_amount;
        this.img = img;
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

    public String getPreparation() {
        return preparation;
    }

    public String getRecipe_amount() {
        return recipe_amount;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }
}
