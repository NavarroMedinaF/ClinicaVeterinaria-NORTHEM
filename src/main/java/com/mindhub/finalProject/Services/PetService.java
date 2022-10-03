package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Pet;

import java.util.List;

public interface PetService {

    List<Pet> findAllPets();

    Pet findPetById(Long id);

    void savePet(Pet pet);

    void deletePet(Pet pet);
}
