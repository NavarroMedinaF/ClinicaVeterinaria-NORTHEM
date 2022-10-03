package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.BalancedMeal;
import com.mindhub.finalProject.models.ProductCategory;

public class BalancedMealDTO {

    private long id;
    private String name, brand, description, image, portion;
    private int quantity, stock;
    private double price;
    private ProductCategory productCategory;

    public BalancedMealDTO(BalancedMeal balancedMeal) {
        this.id = balancedMeal.getId();
        this.name = balancedMeal.getName();
        this.brand = balancedMeal.getBrand();
        this.description = balancedMeal.getDescription();
        this.image = balancedMeal.getImage();
        this.portion = balancedMeal.getPortion();
        this.quantity = balancedMeal.getQuantity();
        this.stock = balancedMeal.getStock();
        this.price = balancedMeal.getPrice();
        this.productCategory = balancedMeal.getCategory();
    }

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

    public String getPortion() {
        return portion;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
