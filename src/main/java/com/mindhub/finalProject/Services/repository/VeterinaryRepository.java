package com.mindhub.finalProject.Services.repository;

import com.mindhub.finalProject.models.Veterinary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VeterinaryRepository extends JpaRepository<Veterinary, Long> {
    Veterinary findByPhoneNumber(String number);
    Veterinary findByEmail(String email);
}
