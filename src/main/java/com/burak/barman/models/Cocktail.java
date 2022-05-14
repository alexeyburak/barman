package com.burak.barman.models;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Cocktail {

    //SELECT GROUP_CONCAT(cocktails.id), cocktails.name, GROUP_CONCAT(recipe.id_ingredient),GROUP_CONCAT(recipe.amount), GROUP_CONCAT(cocktails.preparation), GROUP_CONCAT(cocktails.img)
    //FROM cocktails left join recipe on recipe.id_cocktail=cocktails.id left join
    //ingredients on recipe.id_ingredient=ingredients.id Group by name
    private int id;
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
        return preparation;
    }

    public void setIngredients(String preparation) {
        this.preparation = preparation;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRecipe_amount() {
        return recipe_amount;
    }

}
