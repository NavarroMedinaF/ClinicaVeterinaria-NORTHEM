package com.mindhub.finalProject.controllers;


import com.mindhub.finalProject.DTO.PrepaidDTO;
import com.mindhub.finalProject.Services.CategoryService;
import com.mindhub.finalProject.Services.PrepaidService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Category;
import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Prepaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PrepaidController {

    @Autowired
    PrepaidService prepaidService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/prepaid")
    public List<PrepaidDTO> getAllPrepaid(){
        return prepaidService.findPrepaids().stream().map(PrepaidDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/prepaid/{id}")
    public PrepaidDTO getPrepaidById(@PathVariable Long id){
        return new PrepaidDTO(prepaidService.findPrepaidById(id));
    }

    @PatchMapping("/clients/prepaid/{id}")
    public ResponseEntity<Object> modifyPrepaid(@PathVariable long id, @RequestParam long idCategory, Authentication authentication){

        Client authClient = userService.findClientByEmail(authentication.getName());
        Prepaid prepaid = prepaidService.findPrepaidById(id);
        Category newCategory = categoryService.findCategoryById(idCategory);

        if (authClient==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar este cambio de categoria", HttpStatus.FORBIDDEN);
        }

        if (prepaid==null){
            return new ResponseEntity<>("Usted no posee Obra Social", HttpStatus.FORBIDDEN);
        }

        if (newCategory==null){
            return new ResponseEntity<>("La categoria seleccionada no existe entre nuestras ofertas", HttpStatus.FORBIDDEN);
        }

        if(newCategory == prepaid.getCategory()){
            return new ResponseEntity<>("Usted ya posee esta categoria de Obra social, elija otra por favor.", HttpStatus.FORBIDDEN);
        }

        prepaid.setCategory(newCategory);
        prepaidService.savePrepaid(prepaid);

        return new ResponseEntity<>("Felicitaciones por la nueva categoria de Obra social adquirida", HttpStatus.CREATED);
    }
    @PostMapping("/clients/prepaid")
    public ResponseEntity<Object> addPrepaid(Authentication authentication, @RequestParam long idCategory){

        Client authClient = userService.findClientByEmail(authentication.getName());
        Category category = categoryService.findCategoryById(idCategory);

        if (authClient==null){
            return new ResponseEntity<>("Usted no es un cliente de nuestra Intitucion",HttpStatus.FORBIDDEN);
        }

        if (category==null){
            return new ResponseEntity<>("La categoria a la que usted quiere acceder no existe, elija una correcta", HttpStatus.FORBIDDEN);
        }
        if(authClient.getPrepaid().size() > 0){
            return new ResponseEntity<>("Usted ya posee una Obra social",HttpStatus.FORBIDDEN);
        }
        Prepaid prepaid = new Prepaid(authClient, category);
        prepaidService.savePrepaid(prepaid);

        return new ResponseEntity<>("Ya pudo adquirir la Obra Social para mascotas con categoria " + category.getName() + ".",HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/prepaid")
    public ResponseEntity<Object> deletePrepaid(Authentication authentication, @RequestParam long idPrepaid){

        Client authClient = userService.findClientByEmail(authentication.getName());
        Prepaid prepaid = prepaidService.findPrepaidById(idPrepaid);

        if (authClient==null){
            return new ResponseEntity<>("Usted no posee autoridad para hacer dicha operacion",HttpStatus.FORBIDDEN);
        }

        if (prepaid==null){
            return  new ResponseEntity<>( "Usted  no posee Obra social asociada a su cuenta", HttpStatus.FORBIDDEN);
        }

        Set<Prepaid> prepaids = authClient.getPrepaid();
        prepaids.forEach(prepaid1 ->{
            prepaid1.setCategory(null);
            prepaid1.setClient(null);
            prepaidService.savePrepaid(prepaid1);
        });


        return new ResponseEntity<>("Acaba de dar de baja su Obra Social exitosamente", HttpStatus.CREATED);
    }
}
