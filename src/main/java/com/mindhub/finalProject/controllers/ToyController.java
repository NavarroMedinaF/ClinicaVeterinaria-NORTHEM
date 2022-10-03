package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.ToyDTO;
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
public class ToyController {
    @Autowired
    ProductService productService;

    @GetMapping("/toys")
    public Set<ToyDTO> getAllToys() {
        return productService.findToys().stream().map(ToyDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/toys/{id}")
    public ToyDTO getToyById(@PathVariable Long id){
        return new ToyDTO(productService.findToyById(id));
    }
}
