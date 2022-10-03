package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Veterinary;
import com.mindhub.finalProject.models.VeterinaryCategory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VeterinaryDTO {
    private long id;
    private String name, lastName, phoneNumber,emial;

    private VeterinaryCategory veterinaryCategory;
    private Set<ShiftDTO> shifts;

    private List<String> schedules;
    public VeterinaryDTO() {
    }

    public VeterinaryDTO(Veterinary veterinary) {
        this.id = veterinary.getId();
        this.name = veterinary.getName();
        this.lastName = veterinary.getLastName();
        this.phoneNumber = veterinary.getPhoneNumber();
        this.veterinaryCategory= veterinary.getVeterinaryCategory();
        this.schedules = veterinary.getSchedules();
        this.shifts= veterinary.getShifts().stream().map(ShiftDTO::new).collect(Collectors.toSet());
        this.emial = veterinary.getEmail();
    }

    //* GETTERS

    public long getId() {
        return id;
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

    public Set<ShiftDTO> getShifts() {
        return shifts;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public String getEmial() {
        return emial;
    }
}
