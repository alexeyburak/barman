package com.burak.barman.models;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Cocktail {

    private final int id;
    private String name;
    private String recipe;
    private String preparation;
    private String recipe_amount;
    private String img;


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

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getRecipe_amount() {
        return recipe_amount;
    }

    public void setRecipe_amount(String recipe_amount) {
        this.recipe_amount = recipe_amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }
}
