package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Product;
import com.mindhub.finalProject.models.ProductCategory;

public class ProductDTO {

    private long id;

    private String name, image,description;

    private int stock, quantity;

    private double price;

    private ProductCategory productCategory;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.stock = product.getStock();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.productCategory = product.getCategory();
        this.image = product.getImage();
        this.description = product.getDescription();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getImage() {
        return image;
    }


    public String getDescription() {
        return description;
    }
}

