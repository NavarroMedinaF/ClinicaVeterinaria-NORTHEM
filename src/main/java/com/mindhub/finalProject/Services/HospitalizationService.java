package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Hospitalization;

import java.util.List;

public interface HospitalizationService {
    List<Hospitalization> findHospitalization();

    Hospitalization findHospitalizationById(Long id);

    void saveHospitalization(Hospitalization hospitalization);

    void deleteHospitalization(Hospitalization hospitalization);
}
