package com.mindhub.finalProject.models;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
public class Client extends Usuario{

    private String name, lastName, address, city, state;

    private int postalCode;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Shift> shifts;

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    private Set<Pet> pets;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Purchase> purchases;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Prepaid> prepaid;

    public Client() {}

    public Client(long dni, String email, String password,
                  Authority authority, String name,
                  String lastName, String address, String city,
                  String state, LocalDate dateOfBirth, int postalCode) {
        super(dni, email, password, authority);
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.dateOfBirth = dateOfBirth;
        this.postalCode = postalCode;
    }

    /*GETTERS*/

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getPostalCode() {
        return postalCode;
    }


    public Set<Pet> getPets() {
        return pets;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Set<Prepaid> getPrepaid() {
        return prepaid;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    /*SETTERS*/
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void setPrepaid(Set<Prepaid> prepaid) {
        this.prepaid = prepaid;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }
}
