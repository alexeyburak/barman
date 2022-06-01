package com.burak.barman.models;

/**
 * Barman
 * Created by Alexey Burak
 */

public class Ingredient {

    private int id;
    private String title;
    private int fortress;
    private String category;

    public Ingredient(String title, int id, int fortress, String category) {
        this.title = title;
        this.id = id;
        this.fortress = fortress;
        this.category = category;
    }

    public String getTitle() {
        return title;
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

    public String getCategory() {
        return category;
    }
}
