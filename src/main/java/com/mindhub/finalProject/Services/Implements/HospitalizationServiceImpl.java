package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.HospitalizationService;
import com.mindhub.finalProject.models.Hospitalization;
import com.mindhub.finalProject.Services.repository.HospitalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalizationServiceImpl implements HospitalizationService {

    @Autowired
    HospitalizationRepository hospitalizationRepository;

    @Override
    public List<Hospitalization> findHospitalization() {
        return hospitalizationRepository.findAll();
    }

    @Override
    public Hospitalization findHospitalizationById(Long id){
        return hospitalizationRepository.findById(id).orElse(null);
    }

    @Override
    public void saveHospitalization(Hospitalization hospitalization){
        hospitalizationRepository.save(hospitalization);
    }

    @Override
    public void deleteHospitalization(Hospitalization hospitalization){
        hospitalizationRepository.delete(hospitalization);
    }
}
