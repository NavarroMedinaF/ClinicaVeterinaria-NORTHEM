package com.mindhub.finalProject.models;

import javax.persistence.Entity;

@Entity
public class BalancedMeal extends Product{

    private String portion;

    public BalancedMeal() {
    }

    public BalancedMeal(String name, String brand, String description, String image, int stock, int quantity, double price, ProductCategory category, String portion) {
        super(name, brand, description, image, stock, quantity, price, category);
        this.portion = portion;
    }

    /*GETTERS*/

    public String getPortion() {
        return portion;
    }

    /*SETTERS*/

    public void setPortion(String portion) {
        this.portion = portion;
    }
}
