package com.mindhub.finalProject.controllers;


import com.mindhub.finalProject.DTO.ShiftDTO;
import com.mindhub.finalProject.Services.PetService;
import com.mindhub.finalProject.Services.ShiftService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Pet;
import com.mindhub.finalProject.models.Shift;
import com.mindhub.finalProject.models.Veterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShiftController {

    @Autowired
    ShiftService shiftService;

    @Autowired
    UserService userService;

    @Autowired
    PetService petService;

    @GetMapping("/shifts")
    public Set<ShiftDTO> getAllShifts() {
        return shiftService.findShifts().stream().map(ShiftDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/shifts/{id}")
    public ShiftDTO getShiftById(@PathVariable Long id){
        return new ShiftDTO(shiftService.findShiftById(id));
    }

    @PostMapping("/pets/shifts")
        public ResponseEntity<Object> createShiftPet(@RequestParam Long idVet, @RequestParam Long idPet,
                                              @RequestParam LocalDateTime date, Authentication authentication){

        Veterinary authVeterinary = userService.findVeterinaryByEmail(authentication.getName());
        Client authClient = userService.findClientByEmail(authentication.getName());
        Veterinary selectedVet = userService.findVeterinaryById(idVet);
        Pet selectedPet = petService.findPetById(idPet);

        if (authVeterinary == null && authClient == null){
            return new ResponseEntity<>("No se pudo autenticar el usuario. Por favor, intenta autenticarte nuevamente.", HttpStatus.FORBIDDEN);
        }
        if (selectedPet == null){
            return new ResponseEntity<>("No ha seleccionado una mascota.", HttpStatus.FORBIDDEN);
        }
        if (selectedVet == null){
            return new ResponseEntity<>("No ha seleccionado un veterinario.", HttpStatus.FORBIDDEN);
        }

        Shift newShift = new Shift(selectedPet, date, selectedVet, "Cosas");
        selectedPet.getShift().add(newShift);
        shiftService.saveShift(newShift);

        return new ResponseEntity<>("Turno tomado con exito.", HttpStatus.CREATED);

    }
    @PostMapping("/clients/shifts")
    public ResponseEntity<Object> createShiftClient(@RequestParam Long idVet, @RequestParam LocalDateTime date,
                                                    Authentication authentication){

        Client authClient = userService.findClientByEmail(authentication.getName());
        Veterinary selectedVet = userService.findVeterinaryById(idVet);

        if (authClient == null){
            return new ResponseEntity<>("No puede realizar esta operacion.", HttpStatus.FORBIDDEN);
        }
        if (selectedVet == null){
            return new ResponseEntity<>("No ha seleccionado un veterinario.", HttpStatus.FORBIDDEN);
        }

        Shift newShift = new Shift(authClient, selectedVet, date, "Cositas");
        authClient.getShifts().add(newShift);
        shiftService.saveShift(newShift);

        return new ResponseEntity<>("Turno tomado con exito.", HttpStatus.CREATED);

    }
}
