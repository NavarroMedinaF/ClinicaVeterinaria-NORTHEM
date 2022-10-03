package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.VaccineDTO;
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
public class VaccineController {

    @Autowired
    ProductService productService;

    @GetMapping("/vaccines")
    public Set<VaccineDTO> getAllVaccines() {
        return productService.findVaccines().stream().map(VaccineDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/vaccines/{id}")
    public VaccineDTO getVaccineById(@PathVariable Long id){
        return new VaccineDTO(productService.findVaccioneById(id));
    }
}
