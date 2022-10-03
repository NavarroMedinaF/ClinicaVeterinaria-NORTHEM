package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name, brand,description,image;

    private int stock, quantity;

    private double price;

    private ProductCategory category;


    public Product() {
    }

    public Product(String name, String brand, String description, String image, int stock, int quantity, double price, ProductCategory category) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public Product(String name) {
        this.name = name;

    }

    /* GETTERS */

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }



    /* SETTERS */

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
