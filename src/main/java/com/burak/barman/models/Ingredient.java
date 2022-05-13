package com.burak.barman.models;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Ingredient {

    private int id;
    private String title;
    private int fortress; //крепость
    private String category;

    public Ingredient(String title, int id, int quantity, String category) {
        this.title = title;
        this.id = id;
        this.fortress = quantity;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getFortress() {
        return fortress;
    }

    public void setFortress(int fortress) {
        this.fortress = fortress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
