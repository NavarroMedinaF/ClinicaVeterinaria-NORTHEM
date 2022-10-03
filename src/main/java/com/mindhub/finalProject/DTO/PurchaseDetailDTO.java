package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Product;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

public class PurchaseDetailDTO {

    private List<Product> products;

    private double amount;

    public PurchaseDetailDTO() {
    }


    /*GETTERS*/
    public List<Product> getProducts() {
        return products;
    }

    public double getAmount() {
        return amount;
    }
}
