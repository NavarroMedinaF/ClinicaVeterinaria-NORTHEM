package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Hospitalization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="medicalHistory_id")
    private MedicalHistory medicalHistory;

    private LocalDateTime entryDate, exitDate;
    private String reason;

    public Hospitalization() {}

    public Hospitalization(MedicalHistory medicalHistory, LocalDateTime entryDate,
                            String reason) {
        this.medicalHistory = medicalHistory;
        this.entryDate = entryDate;
        this.reason = reason;
    }

    /* GETTERS */

    public long getId() {
        return id;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
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

    /* SETTERS */

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
