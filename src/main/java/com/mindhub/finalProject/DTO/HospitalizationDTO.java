package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Hospitalization;

import java.time.LocalDateTime;

public class HospitalizationDTO {
    private long id;
    private LocalDateTime entryDate, exitDate;
    private String reason;

    public HospitalizationDTO() {
    }

    public HospitalizationDTO(Hospitalization hospitalization) {
        this.id = hospitalization.getId();
        this.entryDate = hospitalization.getEntryDate();
        this.exitDate = hospitalization.getExitDate();
        this.reason = hospitalization.getReason();
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public String getReason() {
        return reason;
    }
}
