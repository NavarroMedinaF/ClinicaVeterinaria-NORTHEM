package com.mindhub.finalProject.models;

import javax.persistence.Entity;

@Entity
public class Medicine extends Product{

    private String dose, frequency;

    public Medicine() {
    }

    public Medicine(String name, String brand, String description, String image, int stock, int quantity, double price, ProductCategory category, String dose, String frequency) {
        super(name, brand, description, image, stock, quantity, price, category);
        this.dose = dose;
        this.frequency = frequency;
    }

    /*GETTERS*/

    public String getDose() {
        return dose;
    }

    public String getFrequency() {
        return frequency;
    }

    /*SETTERS*/

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
