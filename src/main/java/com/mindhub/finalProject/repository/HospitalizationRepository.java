package com.mindhub.finalProject.repository;

import com.mindhub.finalProject.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {
}
