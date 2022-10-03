package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.MedicalHistoryService;
import com.mindhub.finalProject.models.MedicalHistory;
import com.mindhub.finalProject.Services.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public List<MedicalHistory> findMedicalHistories() {
        return medicalHistoryRepository.findAll();
    }
    @Override
    public MedicalHistory findMedicalHistoryById(Long id){
        return medicalHistoryRepository.findById(id).orElse(null);
    }
    @Override
    public void saveMedicalHistory(MedicalHistory medicalHistory){
        medicalHistoryRepository.save(medicalHistory);
    }
    @Override
    public void deleteMedicalHistory(MedicalHistory medicalHistory){
        medicalHistoryRepository.delete(medicalHistory);
    }
}
