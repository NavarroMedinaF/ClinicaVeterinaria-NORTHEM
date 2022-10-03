package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.PurchaseService;
import com.mindhub.finalProject.models.Purchase;
import com.mindhub.finalProject.Services.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findPurchaseById(Long id){
        return purchaseRepository.findById(id).orElse(null);
    }

    @Override
    public void savePurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(Purchase purchase){
        purchaseRepository.delete(purchase);
    }
}
