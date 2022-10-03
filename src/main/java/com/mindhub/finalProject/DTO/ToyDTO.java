package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.ProductCategory;
import com.mindhub.finalProject.models.Toy;

public class ToyDTO {
    private long id;
    private String name, brand, description, image;
    private int quantity, stock;
    private double price;
    private ProductCategory productCategory;

    public ToyDTO(Toy toy) {
        this.id = toy.getId();
        this.name = toy.getName();
        this.brand = toy.getBrand();
        this.description = toy.getDescription();
        this.image = toy.getImage();
        this.quantity = toy.getQuantity();
        this.stock = toy.getStock();
        this.price = toy.getPrice();
        this.productCategory = toy.getCategory();
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
