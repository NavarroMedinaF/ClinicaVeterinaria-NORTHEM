package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Shift;

import java.util.List;

public interface ShiftService {
    List<Shift> findShifts();

    Shift findShiftById(Long id);

    void saveShift(Shift shift);

    void deleteShift(Shift shift);
}
