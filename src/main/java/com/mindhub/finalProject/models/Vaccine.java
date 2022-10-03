package com.mindhub.finalProject.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vaccine extends Product{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pet_id")
    private Pet pet;

    private String dose;

    public Vaccine() {
    }

    public Vaccine(String name, String brand, String description, String image, int stock, int quantity, double price, ProductCategory category, Pet pet, String dose) {
        super(name, brand, description, image, stock, quantity, price, category);
        this.pet = pet;
        this.dose = dose;
    }

    public Vaccine(String name, Pet pet, String dose) {
        super(name);
        this.pet = pet;
        this.dose = dose;
    }

    /* GETTERS */

    public Pet getPet() {
        return pet;
    }

    public String getDose() {
        return dose;
    }
    /* SETTERS */

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
