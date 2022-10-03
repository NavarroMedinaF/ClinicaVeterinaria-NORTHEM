package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PurchaseDTO {

    private long id;
    private double amount;
    private LocalDateTime datePurchase;

    public PurchaseDTO() {
    }

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.amount = purchase.getAmount();
        this.datePurchase = purchase.getDatePurchase();
    }

    //* GETTERS

    public long getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDatePurchase() {
        return datePurchase;
    }
}
