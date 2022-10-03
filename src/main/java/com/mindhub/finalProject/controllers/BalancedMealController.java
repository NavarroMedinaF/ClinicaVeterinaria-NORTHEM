package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.BalancedMealDTO;
import com.mindhub.finalProject.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BalancedMealController {

    @Autowired
    ProductService productService;

    @GetMapping("/balancedmeals")
    public Set<BalancedMealDTO> getAllBalancedMeals() {
        return productService.findBalancedMeals().stream().map(BalancedMealDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/balancedmeals/{id}")
    public BalancedMealDTO getBalancedMealById(@PathVariable Long id){
        return new BalancedMealDTO(productService.findBalancedMealById(id));
    }
}
