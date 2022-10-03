package com.mindhub.finalProject.DTO;

import java.time.LocalDate;

public class PersonalDataDTO {

    private long dni;
    private String name, lastName, city, state, address, email, password;
    private int postalCode;
    private LocalDate dateOfBirth;

    public PersonalDataDTO() {
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
