package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.PrepaidService;
import com.mindhub.finalProject.models.Prepaid;
import com.mindhub.finalProject.Services.repository.PrepaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrepaidServiceImpl implements PrepaidService {

    @Autowired
    PrepaidRepository prepaidRepository;

    @Override
    public List<Prepaid> findPrepaids() {
        return prepaidRepository.findAll();
    }
    @Override
    public Prepaid findPrepaidById(Long id){
        return prepaidRepository.findById(id).orElse(null);
    }
    @Override
    public void savePrepaid(Prepaid prepaid){
        prepaidRepository.save(prepaid);
    }
    @Override
    public void deletePrepaid(Prepaid prepaid){
        prepaidRepository.delete(prepaid);
    }

}
