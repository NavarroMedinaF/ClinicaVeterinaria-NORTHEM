package com.mindhub.finalProject.Services.Implements;

import com.mindhub.finalProject.Services.PetService;
import com.mindhub.finalProject.models.Pet;
import com.mindhub.finalProject.Services.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetRepository petRepository;

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }
    @Override
    public Pet findPetById(Long id){
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public void savePet(Pet pet){
        petRepository.save(pet);
    }

    @Override
    public void deletePet(Pet pet){
        petRepository.delete(pet);
    }
}
