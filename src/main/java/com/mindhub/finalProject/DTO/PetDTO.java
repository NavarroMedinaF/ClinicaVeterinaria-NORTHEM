package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Pet;

import java.util.Set;
import java.util.stream.Collectors;

public class PetDTO {

    private long id;
    private String name, race;
    private int age;
    private float weight;
    private Set<VaccineDTO> vaccines;
    private Set<ShiftDTO> shifts;
    private MedicalHistoryDTO medicalHistory;

    public PetDTO() {
    }

    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.race = pet.getRace();
        this.age = pet.getAge();
        this.weight = pet.getWeight();
        this.vaccines = pet.getVaccines().stream().map(VaccineDTO::new).collect(Collectors.toSet());
        this.shifts = pet.getShift().stream().map(ShiftDTO::new).collect(Collectors.toSet());
        this.medicalHistory = new MedicalHistoryDTO(pet.getMedicalHistory());
    }

    //* GETTERS
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public Set<VaccineDTO> getVaccines() {
        return vaccines;
    }

    public MedicalHistoryDTO getMedicalHistory() {
        return medicalHistory;
    }

    public Set<ShiftDTO> getShifts() {
        return shifts;
    }
}
