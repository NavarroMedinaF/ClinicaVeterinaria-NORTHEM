package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.ProductDTO;
import com.mindhub.finalProject.Services.ProductService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {


    /*METHODS*/


    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/products")
    public Set<ProductDTO> getAllProducts() {
        return productService.findProducts().stream().map(ProductDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return new ProductDTO(productService.findProductById(id));
    }

    @PostMapping("/admin/product")
    public ResponseEntity<Object> createProduct(@RequestParam String name, @RequestParam String brand,
                                                @RequestParam String description, @RequestParam String image,
                                                @RequestParam int stock, @RequestParam double price, @RequestParam ProductCategory productCategory, Authentication authentication){

        Admin admin = userService.findAdminByEmail(authentication.getName());


        if(name.isEmpty()||brand.isEmpty()||description.isEmpty()){
            return new ResponseEntity<>("Revise los datos por favor, estan incompletos", HttpStatus.FORBIDDEN);
        }

        if(!image.contains("http")){
            return  new ResponseEntity<>("Por favor revise la URL del enlace",HttpStatus.FORBIDDEN);
        }

        if(productCategory==null){
            return new ResponseEntity<>("Seleccione una categoria valida",HttpStatus.BAD_REQUEST);
        }

        if (admin==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar esta accion",HttpStatus.FORBIDDEN);
        }

        productService.saveProduct(new Product(name,brand,description,image,stock,0,price, productCategory));

        return new ResponseEntity<>("El producto fue agregado a la base de Datos exitosamente",HttpStatus.CREATED);

    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable long id, Authentication authentication){

        Admin admin = userService.findAdminByEmail(authentication.getName());
        Product product = productService.findProductById(id);

        if(admin==null){
            return  new ResponseEntity<>("Usted no posee autoridad para realizar esta accion",HttpStatus.FORBIDDEN);
        }

        if(product==null){
            return  new ResponseEntity<>("El producto q desea borrar no existe.",HttpStatus.FORBIDDEN);
        }

        productService.deleteProduct(product);
         return new ResponseEntity<>("El producto a sido borrado exitosamente",HttpStatus.CREATED);
    }
}

