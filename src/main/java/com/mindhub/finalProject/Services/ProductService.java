package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.*;

import java.util.List;

public interface ProductService {

    /*PRODUCT*/
    List<Product> findProducts();

    Product findProductById(Long id);

    void saveProduct(Product product);

    void deleteProduct(Product product);


    /*TOY*/
    List<Toy> findToys();

    Toy findToyById(Long id);

    void saveToy(Toy toy);

    void deleteToy(Toy toy);


    /*VACCINE*/
    List<Vaccine> findVaccines();

    Vaccine findVaccioneById(Long id);

    void saveVaccine(Vaccine vaccine);

    void deleteVaccine(Vaccine vaccine);


    /*BALANCEDMEAL*/
    List<BalancedMeal> findBalancedMeals();

    BalancedMeal findBalancedMealById(Long id);

    void saveBalancedMeal(BalancedMeal balancedMeal);

    void deleteBalancedMeal(BalancedMeal balancedMeal);


    /*MEDICINE*/
    List<Medicine> findMedicines();

    Medicine findMedicineById(Long id);

    void saveMedicine(Medicine medicine);

    void deleteBalancedMeal(Medicine medicine);
}
