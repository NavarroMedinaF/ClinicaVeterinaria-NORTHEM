package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.MedicalHistory;

import java.util.List;

public interface MedicalHistoryService {

    List<MedicalHistory> findMedicalHistories();

    MedicalHistory findMedicalHistoryById(Long id);

    void saveMedicalHistory(MedicalHistory medicalHistory);

    void deleteMedicalHistory(MedicalHistory medicalHistory);
}
