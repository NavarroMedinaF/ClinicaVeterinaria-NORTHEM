package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.ShiftService;
import com.mindhub.finalProject.models.Shift;
import com.mindhub.finalProject.Services.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public List<Shift> findShifts() {
        return shiftRepository.findAll();
    }

    @Override
    public Shift findShiftById(Long id){
        return shiftRepository.findById(id).orElse(null);
    }

    @Override
    public void saveShift(Shift shift){
        shiftRepository.save(shift);
    }

    @Override
    public void deleteShift(Shift shift){
        shiftRepository.delete(shift);
    }
}
