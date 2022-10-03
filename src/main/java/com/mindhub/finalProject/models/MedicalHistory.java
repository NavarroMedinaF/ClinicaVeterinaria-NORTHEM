package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToOne
    private Pet pet;

    @OneToMany(mappedBy = "medicalHistory", fetch = FetchType.EAGER)
    @ElementCollection
    private Set<Hospitalization> hospitalizations;

    private String petName, race;

    private double weight;

    private PetType petType;

    private String observation;


    public MedicalHistory() {
    }

    public MedicalHistory(Pet pet, String petName, String race,
                          double weight, PetType petType, String observation) {
        this.pet = pet;
        this.petName = petName;
        this.race = race;
        this.weight = weight;
        this.petType = petType;
        this.observation = observation;
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public Set<Hospitalization> getHospitalizations() {
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

    /*SETTERS*/

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setHospitalizations(Set<Hospitalization> hospitalizations) {
        this.hospitalizations = hospitalizations;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
