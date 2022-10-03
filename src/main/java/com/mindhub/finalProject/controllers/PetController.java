package com.mindhub.finalProject.controllers;


import com.mindhub.finalProject.DTO.ModifyPetDTO;
import com.mindhub.finalProject.DTO.PetDTO;
import com.mindhub.finalProject.Services.*;
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
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;

    @Autowired
    MedicalHistoryService medicalHistoryService;

    @Autowired
    ShiftService shiftService;

    @Autowired
    HospitalizationService hospitalizationService;

    @Autowired
    ProductService productService;

    @GetMapping("/pets")
    public Set<PetDTO> getAllPets(){
        return petService.findAllPets().stream().map(PetDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/pets/{id}")
    public PetDTO getPet(@PathVariable Long id){
        return new PetDTO(petService.findPetById(id));
    }

    @PostMapping("/clients/pets")
    public ResponseEntity<Object> createPet(@RequestParam long dni, @RequestParam String name,
                                            @RequestParam String race, @RequestParam int age,
                                            @RequestParam float weight, @RequestParam PetType petType,
                                            @RequestParam String observation, Authentication authentication){

        Veterinary authVeterinary = userService.findVeterinaryByEmail(authentication.getName());
        Client authClient = userService.findClientById(dni);

        if (dni < 0 || name.isEmpty() || race.isEmpty() || age < 0 || weight < 0 || petType.toString().isEmpty() || observation.isEmpty()){
            return new ResponseEntity<>("Los campos no fueron completados." +
                    "Revise los datos.", HttpStatus.FORBIDDEN);
        }
        if(authClient==null){
            return new ResponseEntity<>("El cliente no existe.", HttpStatus.FORBIDDEN);
        }
        if(authVeterinary==null){
            return new ResponseEntity<>("No puedes modificar estos datos.", HttpStatus.FORBIDDEN);
        }

        Pet newPet = new Pet(name, race, age, weight, petType, authClient);
        petService.savePet(newPet);

        MedicalHistory newMedicalHistory = new MedicalHistory(newPet, newPet.getName(), newPet.getRace(),
                        newPet.getWeight(), newPet.getPetType(), observation);
        medicalHistoryService.saveMedicalHistory(newMedicalHistory);

        return new ResponseEntity<>("La mascota se a creado con exito",HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/pets")
    public ResponseEntity<Object> deletePet(@RequestParam Long id, Authentication authentication){
        Veterinary veterinary = userService.findVeterinaryByEmail(authentication.getName());
        Pet pet = petService.findPetById(id);

        if (veterinary == null){
            return new ResponseEntity<>("No puedes " +
                    "eliminar una mascota. No tienes autorizacion.", HttpStatus.FORBIDDEN);
        }

        if (pet == null){
            return new ResponseEntity<>("La mascota que " +
                    "desea eliminar, no existe.", HttpStatus.FORBIDDEN);
        }


        Set<Shift> shifts = pet.getShift();
        shifts.forEach(shift ->shiftService.deleteShift(shift));

        MedicalHistory medicalHistory= pet.getMedicalHistory();

        Set<Hospitalization> hospitalizations= medicalHistory.getHospitalizations();
        hospitalizations.forEach(hospitalization -> hospitalizationService.deleteHospitalization(hospitalization));

        Set<Vaccine> vaccines = pet.getVaccines();
        vaccines.forEach(vaccine -> productService.deleteVaccine(vaccine));

        petService.deletePet(pet);
        return new ResponseEntity<>("Se ha eliminado la mascota.", HttpStatus.OK);
    }

    @PatchMapping("/clients/pets")
    public ResponseEntity<Object> modifyClient(@RequestBody ModifyPetDTO modifyPetDTO,
                                               Authentication authentication) {

        Veterinary veterinary = userService.findVeterinaryByEmail(authentication.getName());
        Pet pet = petService.findPetById(modifyPetDTO.getId());

        if(veterinary==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar esta accion",HttpStatus.FORBIDDEN);
        }
        if (pet == null){
            return new ResponseEntity<>("La mascota que desea modificar no existe", HttpStatus.BAD_REQUEST);
        }
        if(modifyPetDTO.getAge()< pet.getAge() ){
            return new ResponseEntity<>("La edad que desea modificar es menor, corrija porfavor.",HttpStatus.BAD_REQUEST);
        }

        if (modifyPetDTO.getWeigth()<0){
            return new ResponseEntity<>("El peso es un dato invalido, por favor revise su peticion", HttpStatus.BAD_REQUEST);
        }

        pet.setAge(modifyPetDTO.getAge());
        pet.setWeight(modifyPetDTO.getWeigth());
        petService.savePet(pet);

        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }
}
