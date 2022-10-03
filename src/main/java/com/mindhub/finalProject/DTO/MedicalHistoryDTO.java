package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.MedicalHistory;
import com.mindhub.finalProject.models.PetType;

import java.util.Set;
import java.util.stream.Collectors;

public class MedicalHistoryDTO {
    private long id;
    private Set<HospitalizationDTO> hospitalizations;
    private String petName, race, observation;
    private double weight;
    private PetType petType;

    public MedicalHistoryDTO() {
    }

    public MedicalHistoryDTO(MedicalHistory medicalHistory) {
        this.id = medicalHistory.getId();
        this.hospitalizations = medicalHistory.getHospitalizations().stream()
                .map(HospitalizationDTO::new).collect(Collectors.toSet());
        this.petName = medicalHistory.getPetName();
        this.race = medicalHistory.getRace();
        this.weight = medicalHistory.getWeight();
        this.petType = medicalHistory.getPetType();
        this.observation = medicalHistory.getObservation();
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public Set<HospitalizationDTO> getHospitalizations() {
        return hospitalizations;
    }

    public String getPetName() {
        return petName;
    }

    public String getRace() {
        return race;
    }

    public double getWeight() {
        return weight;
    }

    public PetType getPetType() {
        return petType;
    }

    public String getObservation() {
        return observation;
    }
}
