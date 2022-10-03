package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Client;
import com.mindhub.finalProject.models.Pet;
import com.mindhub.finalProject.models.Prepaid;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long dni;
    private String name, lastName, address, city, state, email, password;
    private int postalCode;
    private LocalDate dateOfBirth;
    private Set<PetDTO> pets;
    private Set<PurchaseDTO> purchases;
    private Set<CardDTO> cards;
    private Set<PrepaidDTO> prepaid;
    private Set<ShiftDTO> shifts;

    public ClientDTO(Client client) {
        this.dni = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.address = client.getAddress();
        this.city = client.getCity();
        this.state = client.getState();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.dateOfBirth = client.getDateOfBirth();
        this.postalCode = client.getPostalCode();
        this.pets = client.getPets().stream().map(PetDTO::new).collect(Collectors.toSet());
        this.purchases = client.getPurchases().stream().map(PurchaseDTO::new).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
        this.prepaid = client.getPrepaid().stream().map(PrepaidDTO::new).collect(Collectors.toSet());
        this.shifts = client.getShifts().stream().map(ShiftDTO::new).collect(Collectors.toSet());
    }

    public long getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public Set<PetDTO> getPets() {
        return pets;
    }

    public Set<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public Set<PrepaidDTO> getPrepaid() {
        return prepaid;
    }

    public Set<ShiftDTO> getShifts() {
        return shifts;
    }
}
