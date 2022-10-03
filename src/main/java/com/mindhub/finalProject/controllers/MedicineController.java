package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.MedicineDTO;
import com.mindhub.finalProject.DTO.ToyDTO;
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
public class MedicineController {

    @Autowired
    ProductService productService;

    @GetMapping("/medicines")
    public Set<MedicineDTO> getAllToys() {
        return productService.findMedicines().stream().map(MedicineDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/medicines/{id}")
    public MedicineDTO getToyById(@PathVariable Long id){
        return new MedicineDTO(productService.findMedicineById(id));
    }
}
