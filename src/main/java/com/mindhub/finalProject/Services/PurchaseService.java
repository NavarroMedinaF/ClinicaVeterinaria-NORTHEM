package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAllPurchases();

    Purchase findPurchaseById(Long id);

    void savePurchase(Purchase purchase);

    void deletePurchase(Purchase purchase);
}
