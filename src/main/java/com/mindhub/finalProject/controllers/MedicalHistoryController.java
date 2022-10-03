package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.MedicalHistoryDTO;
import com.mindhub.finalProject.Services.MedicalHistoryService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.MedicalHistory;
import com.mindhub.finalProject.models.Veterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MedicalHistoryController {


    @Autowired
    MedicalHistoryService medicalHistoryService;

    @Autowired
    UserService userService;


    @GetMapping("/medicalHistory")
    public Set<MedicalHistoryDTO> getAllVeterinaries() {
        return medicalHistoryService.findMedicalHistories().stream().map(MedicalHistoryDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/medicalHistory/{id}")
    public MedicalHistoryDTO getVeterinaryById(@PathVariable Long id){
        return new MedicalHistoryDTO(medicalHistoryService.findMedicalHistoryById(id));
    }

    @PatchMapping("/medicalHistory/{id}")
    public ResponseEntity<Object> modifyMedicalHistory(@RequestParam String observation, @RequestParam  double weight,
                                                       @PathVariable long id, Authentication authentication){

        Veterinary veterinary = userService.findVeterinaryByEmail(authentication.getName());
        MedicalHistory medicalHistory = medicalHistoryService.findMedicalHistoryById(id);

        if(veterinary== null){
            return new ResponseEntity<>("Usted no tiene autoridad para modificar este archivo", HttpStatus.FORBIDDEN);
        }

        if(medicalHistory==null){
            return  new ResponseEntity<>("La historia medica no existe, compruebe los datos de la peticon", HttpStatus.FORBIDDEN);
        }

        if (observation.isEmpty()){
            return  new ResponseEntity<>( "Faltan los datos de la observacion",HttpStatus.FORBIDDEN);

        }

        if (weight<0){
            return new ResponseEntity<>("Ingrese un peso valido",HttpStatus.FORBIDDEN);
        }

        medicalHistory.setObservation(medicalHistory.getObservation()+observation);
        medicalHistory.setWeight(weight);
        medicalHistoryService.saveMedicalHistory(medicalHistory);

        return new ResponseEntity<>("Se ah actualizado la historia medica",HttpStatus.CREATED);
    }

}
