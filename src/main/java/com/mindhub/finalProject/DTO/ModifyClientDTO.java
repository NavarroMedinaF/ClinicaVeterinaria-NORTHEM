package com.mindhub.finalProject.DTO;


import java.time.LocalDate;

public class ModifyClientDTO {

    private String name, lastName, address, city, state, email, password;
    private int postalCode;

    public ModifyClientDTO() {
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPostalCode() {
        return postalCode;
    }
}
