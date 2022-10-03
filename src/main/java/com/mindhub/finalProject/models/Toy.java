package com.mindhub.finalProject.models;

import javax.persistence.Entity;

@Entity
public class Toy extends Product{

    public Toy() {
    }

    public Toy(String name, String brand, String description,
               String image, int stock, int quantity, double price, ProductCategory category) {
        super(name, brand, description, image, stock, quantity, price, category);
    }


}
