package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.CategoryDTO;
import com.mindhub.finalProject.DTO.ProductDTO;
import com.mindhub.finalProject.Services.CategoryService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Admin;
import com.mindhub.finalProject.models.Category;
import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/category")
    public Set<CategoryDTO> getAllCategory() {
        return categoryService.findCategories().stream().map(CategoryDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/category/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return new CategoryDTO(categoryService.findCategoryById(id));
    }



    @PostMapping("/admin/category")
    public ResponseEntity<Object> createCategory(@RequestParam double price, @RequestParam double discount,
                                                 @RequestParam String name, Authentication authentication){

        Admin admin = userService.findAdminByEmail(authentication.getName());
        Category category = categoryService.findCategoryByName(name);

        if(admin==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar esta accion",HttpStatus.FORBIDDEN);
        }
        if(price<1){
            return  new ResponseEntity<>("El precio es invalido", HttpStatus.FORBIDDEN);
        }

        if (discount<0&&discount>1){
            return  new ResponseEntity<>("El valor del descuento es invalido", HttpStatus.FORBIDDEN);
        }

        if(name.isEmpty()){
            return new ResponseEntity<>("El nombre es invalido", HttpStatus.FORBIDDEN);
        }

        if(category!=null){
            return new ResponseEntity<>("La categoria " + name + " ya existe en la base de datos, elija otro nombre",HttpStatus.FORBIDDEN );
        }

        categoryService.saveCategory(new Category(price,discount,name));

         return new ResponseEntity<>("Se ha creado la categoria de Obra social",HttpStatus.FORBIDDEN);
    }

    @PatchMapping("/admin/category/{id}")
    public ResponseEntity<Object> modifyCategory(@RequestParam double price, @RequestParam double discount,
                                                 @RequestParam String name, Authentication authentication,
                                                 @PathVariable long id){

        Admin admin = userService.findAdminByEmail(authentication.getName());
        Category category = categoryService.findCategoryById(id);

        if(admin==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar esta accion",HttpStatus.FORBIDDEN);
        }
        if(price<1){
            return  new ResponseEntity<>("El precio es invalido", HttpStatus.FORBIDDEN);
        }

        if (discount<0&&discount>1){
            return  new ResponseEntity<>("El valor del descuento es invalido", HttpStatus.FORBIDDEN);
        }

        if(name.isEmpty()){
            return new ResponseEntity<>("El nombre es invalido", HttpStatus.FORBIDDEN);
        }

        if(category==null){
            return new ResponseEntity<>("La categoria no existe",HttpStatus.FORBIDDEN);
        }

        category.setDiscount(discount);
        category.setName(name);
        category.setPrice(price);
        categoryService.saveCategory(category);
        return new ResponseEntity<>("La categoria se modifico exitosamente", HttpStatus.CREATED);
    }

}
