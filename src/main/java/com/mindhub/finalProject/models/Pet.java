package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_dni")
    private Client client;

    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER)
    private Set<Vaccine> vaccines;

    @OneToMany(mappedBy="pet", fetch=FetchType.EAGER)
    private  Set<Shift> shift;

    @OneToOne(cascade = CascadeType.ALL ,mappedBy="pet")
    private MedicalHistory medicalHistory;

    private String name, race;

    private int age;

    private float weight;

    private PetType petType;

    /*CONTRUCTOR*/
    public Pet() {
    }

    public Pet(String name, String race, int age,
               float weight, PetType petType, Client client) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.weight = weight;
        this.petType = petType;
        this.client = client;
    }

    /*GETTERS*/

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

    public Client getClient() {
        return client;
    }

    public Set<Vaccine> getVaccines() {
        return vaccines;
    }

    public Set<Shift> getShift() {
        return shift;
    }

    public PetType getPetType() {
        return petType;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    /*SETTERS*/

    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVaccines(Set<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void setShift(Set<Shift> shift) {
        this.shift = shift;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }
}