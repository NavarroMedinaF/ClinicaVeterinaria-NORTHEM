package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Medicine;
import com.mindhub.finalProject.models.ProductCategory;

public class MedicineDTO {
    private long id;
    private String name, brand, description, image, dose, frequency;
    private int quantity, stock;
    private double price;
    private ProductCategory productCategory;

    public MedicineDTO(Medicine medicine) {
        this.id = medicine.getId();
        this.name = medicine.getName();
        this.brand = medicine.getBrand();
        this.description = medicine.getDescription();
        this.image = medicine.getImage();
        this.dose = medicine.getDose();
        this.frequency = medicine.getFrequency();
        this.quantity = medicine.getQuantity();
        this.stock = medicine.getStock();
        this.price = medicine.getPrice();
        this.productCategory = medicine.getCategory();
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

    public String getDose() {
        return dose;
    }

    public String getFrequency() {
        return frequency;
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
