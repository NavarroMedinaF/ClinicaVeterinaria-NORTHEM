package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.ProductService;
import com.mindhub.finalProject.Services.repository.*;
import com.mindhub.finalProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ToyRepository toyRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    BalancedMealRepository balancedMealRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    @Override
    public List<Toy> findToys() {
        return toyRepository.findAll();
    }

    @Override
    public Toy findToyById(Long id) {
        return toyRepository.findById(id).orElse(null);
    }

    @Override
    public void saveToy(Toy toy) {
        toyRepository.save(toy);
    }

    @Override
    public void deleteToy(Toy toy) {
        toyRepository.delete(toy);
    }

    @Override
    public List<Vaccine> findVaccines() {
        return vaccineRepository.findAll();
    }

    @Override
    public Vaccine findVaccioneById(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }

    @Override
    public void saveVaccine(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
    }

    @Override
    public void deleteVaccine(Vaccine vaccine) {
        vaccineRepository.delete(vaccine);
    }

    @Override
    public List<BalancedMeal> findBalancedMeals() {
        return balancedMealRepository.findAll();
    }

    @Override
    public BalancedMeal findBalancedMealById(Long id) {
        return balancedMealRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBalancedMeal(BalancedMeal balancedMeal) {
        balancedMealRepository.save(balancedMeal);
    }

    @Override
    public void deleteBalancedMeal(BalancedMeal balancedMeal) {
        balancedMealRepository.delete(balancedMeal);
    }

    @Override
    public List<Medicine> findMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine findMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @Override
    public void deleteBalancedMeal(Medicine medicine) {
        medicineRepository.delete(medicine);
    }

}
