package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Category;

public class CategoryDTO {

    private long id;
    private String name;
    private double price, discount;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.price = category.getPrice();
        this.discount = category.getDiscount();
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }
}
