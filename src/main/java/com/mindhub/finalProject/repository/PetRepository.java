package com.mindhub.finalProject.repository;

import com.mindhub.finalProject.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PetRepository extends JpaRepository<Pet, Long> {
}
