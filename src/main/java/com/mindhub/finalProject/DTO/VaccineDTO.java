package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Vaccine;

public class VaccineDTO {
    private long id;
    private String name, dose;
    private double price;
    private int stock;

    public VaccineDTO() {
    }

    public VaccineDTO(Vaccine vaccine) {
        this.id = vaccine.getId();
        this.name = vaccine.getName();
        this.price = vaccine.getPrice();
        this.stock = vaccine.getStock();
        this.dose = vaccine.getDose();
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }
}
