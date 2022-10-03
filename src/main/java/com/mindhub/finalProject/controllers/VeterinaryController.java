package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.VeterinaryDTO;
import com.mindhub.finalProject.Services.ShiftService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Admin;
import com.mindhub.finalProject.models.Authority;
import com.mindhub.finalProject.models.Veterinary;
import com.mindhub.finalProject.models.VeterinaryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VeterinaryController {
    @Autowired
    UserService userService;

    @Autowired
    ShiftService shiftService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/veterinaries")
    public Set<VeterinaryDTO> getAllVeterinaries() {
        return userService.findAllVeterinaries().stream().map(VeterinaryDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/veterinaries/{id}")
    public VeterinaryDTO getVeterinaryById(@PathVariable Long id){
        return new VeterinaryDTO(userService.findVeterinaryById(id));
    }

    @GetMapping("/veterinaries/current")
    public VeterinaryDTO getVenterinay( Authentication authentication){
        return new VeterinaryDTO(userService.findVeterinaryByEmail(authentication.getName()));
    }

    @PostMapping("/veterinaries")
    public ResponseEntity<Object> createVeterinary(@RequestParam String email, @RequestParam String password,
                                                   @RequestParam String name, @RequestParam String lastName, @RequestParam String direction,
                                                   @RequestParam String  phoneNumber, @RequestParam VeterinaryCategory veterinaryCategory,
                                                   @RequestParam long dni, @RequestParam List<String> list, Authentication authentication){

        Veterinary veterinaryExist =  userService.findVeterinaryById(dni);
        Admin admin = userService.findAdminByEmail(authentication.getName());

        if( veterinaryExist != null){
            return new ResponseEntity<>("El veterinario con el dni "+ dni + " ya existe, compruebe sus datos", HttpStatus.FORBIDDEN );
        }
        if(admin==null){
            return new ResponseEntity<>("Usted no posee autoridad para realizar esta operacion", HttpStatus.FORBIDDEN);
        }
        if (!email.contains("@") || email.equalsIgnoreCase("admin@admin")) {
            return new ResponseEntity<>("El email esta incompleto o es incorrecto", HttpStatus.BAD_REQUEST);
        }
        if (name.length() < 4 || lastName.length() < 3) {
            return new ResponseEntity<>("El nombre y/o el apellido no cumplen con los caracteres minimos", HttpStatus.BAD_REQUEST);
        }
        if (!userService.validator(password)) {
            return new ResponseEntity<>("Contraseña invalida", HttpStatus.BAD_REQUEST);
        }
        if (direction.isEmpty() || phoneNumber.length()<8 || veterinaryCategory==null) {
            return new ResponseEntity<>("Llene correctamente los campos q indican su direccion", HttpStatus.BAD_REQUEST);
        }

        if (dni < 1000000 && dni > 1000000000) {
            return new ResponseEntity<>("Su DNI es incorrecto", HttpStatus.FORBIDDEN);
        }
        if(list.isEmpty()){
            return new ResponseEntity<>("Debe colocar valores de horarios para atencion del veterinario",HttpStatus.BAD_REQUEST);
        }
        Veterinary veterinary = new Veterinary(dni,email,passwordEncoder.encode(password), Authority.VETERINARY,name,lastName,phoneNumber,veterinaryCategory,list);
        userService.saveVeterinary(veterinary);

        return new ResponseEntity<>("El veterinario se ha creado exitosamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/veterinaries/{id}")
    public ResponseEntity<Object> deleteVeterinary(@PathVariable long id, Authentication authentication){

        Admin admin = userService.findAdminByEmail(authentication.getName());
        Veterinary deleteVeterinary=userService.findVeterinaryById(id);

        if(admin==null){
            return new ResponseEntity<>("Usted no posee autoridad para realizar esta accion", HttpStatus.FORBIDDEN);
        }

        if (deleteVeterinary==null){
            return new ResponseEntity<>("El veterinario que quiere eliminar no se encuentra en la Base de Datos", HttpStatus.FORBIDDEN);
        }

        deleteVeterinary.getShifts().forEach(shift -> shiftService.deleteShift(shift));
        userService.deleteVeterinary(deleteVeterinary);

        return new ResponseEntity<>("Se ha eliminado el Veterinario " + deleteVeterinary.getName() + " exitosamente.",HttpStatus.CREATED);
    }
}
