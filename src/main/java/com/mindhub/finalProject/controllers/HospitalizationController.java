package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.HospitalizationDTO;
import com.mindhub.finalProject.DTO.VeterinaryDTO;
import com.mindhub.finalProject.Services.HospitalizationService;
import com.mindhub.finalProject.Services.MedicalHistoryService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Hospitalization;
import com.mindhub.finalProject.models.MedicalHistory;
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
public class HospitalizationController {

    @Autowired
    HospitalizationService hospitalizationService;

    @Autowired
    UserService userService;

    @Autowired
    MedicalHistoryService medicalHistoryService;

    @GetMapping("/hospitalization")
    public Set<HospitalizationDTO> getAllHospitalization() {
        return hospitalizationService.findHospitalization().stream().map(HospitalizationDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/hospitalization/{id}")
    public HospitalizationDTO getHospitalizationById(@PathVariable Long id){
        return new HospitalizationDTO(hospitalizationService.findHospitalizationById(id));
    }

    @PostMapping("/hospitalization")
    public ResponseEntity<Object> createHospitalization(@RequestParam String reason, Authentication authentication,
                                                        @RequestParam long id){

        Veterinary veterinary = userService.findVeterinaryByEmail(authentication.getName());
        MedicalHistory medicalHistory = medicalHistoryService.findMedicalHistoryById(id);

        if(reason.isEmpty()){
            return new ResponseEntity<>("Faltan completar la reason", HttpStatus.FORBIDDEN);
        }
        if (veterinary==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar esta accion", HttpStatus.FORBIDDEN);
        }
         if(medicalHistory==null){
             return new ResponseEntity<>("La mascota no posee Historial medico.", HttpStatus.FORBIDDEN);
         }

        Hospitalization hospitalization= new Hospitalization(medicalHistory,LocalDateTime.now(),reason);
        hospitalizationService.saveHospitalization(hospitalization);


        return new ResponseEntity<>("La hospitalizacion fue creada", HttpStatus.CREATED);
    }

    @PatchMapping("/hospitalization/{id}")
    public ResponseEntity<Object> modifyHospitalization(Authentication authentication, @PathVariable long id){

        Veterinary veterinary = userService.findVeterinaryByEmail(authentication.getName());
        Hospitalization hospitalization = hospitalizationService.findHospitalizationById(id);

        if (veterinary==null){
            return new ResponseEntity<>("Usted no tiene autoridad para realizar esta accion", HttpStatus.FORBIDDEN);
        }
        if(hospitalization==null){
            return new ResponseEntity<>("La hospitalizacion es inexistente", HttpStatus.FORBIDDEN);
        }

        hospitalization.setExitDate(LocalDateTime.now());
        hospitalizationService.saveHospitalization(hospitalization);

        return new ResponseEntity<>("La hospitalizacion fue cerrada",HttpStatus.CREATED);

    }
}
