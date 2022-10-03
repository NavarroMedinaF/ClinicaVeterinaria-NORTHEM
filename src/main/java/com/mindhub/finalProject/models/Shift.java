package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_dni")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="veterinary_id")
    private Veterinary veterinary;

    private LocalDateTime date;

    private String reason;

    public Shift() {
    }

    public Shift(Pet pet, LocalDateTime date, Veterinary veterinary, String reason) {
        this.pet = pet;
        this.date = date;
        this.veterinary = veterinary;
        this.reason = reason;
    }

    public Shift(Client client, Veterinary veterinary, LocalDateTime date, String reason) {
        this.client = client;
        this.veterinary = veterinary;
        this.date = date;
        this.reason = reason;
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Veterinary getVeterinary() {
        return veterinary;
    }

    public Client getClient() {
        return client;
    }

    public String getReason() {
        return reason;
    }

    /*SETTERS*/

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setVeterinary(Veterinary veterinary) {
        this.veterinary = veterinary;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
