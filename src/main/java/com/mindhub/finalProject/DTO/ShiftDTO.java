package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Shift;

import java.time.LocalDateTime;

public class ShiftDTO {
    private long id;
    private LocalDateTime date;
    private String reason;

    public ShiftDTO() {
    }

    public ShiftDTO(Shift shift) {
        this.id = shift.getId();
        this.date = shift.getDate();
        this.reason = shift.getReason();
    }

    //* GETTERS
    public long getId() {
        return id;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }
}
