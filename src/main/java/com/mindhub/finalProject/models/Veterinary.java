package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Veterinary extends Usuario{

    @OneToMany(mappedBy = "veterinary", fetch = FetchType.EAGER)
    private Set<Shift> shifts;

    private String name, lastName, phoneNumber;

    private VeterinaryCategory veterinaryCategory;

    @ElementCollection
    private List<String> schedules = new ArrayList<>();

    public Veterinary() {
    }

    public Veterinary(long dni, String email, String password, Authority authority, String name,
                      String lastName, String phoneNumber, VeterinaryCategory veterinaryCategory, List<String> schedules) {
        super(dni, email, password, authority);
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.veterinaryCategory = veterinaryCategory;
        this.schedules = schedules;
    }

    /* GETTERS */

    public Set<Shift> getShifts() {
        return shifts;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public VeterinaryCategory getVeterinaryCategory() {
        return veterinaryCategory;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    /* SETTERS */

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVeterinaryCategory(VeterinaryCategory veterinaryCategory) {
        this.veterinaryCategory = veterinaryCategory;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }
}
