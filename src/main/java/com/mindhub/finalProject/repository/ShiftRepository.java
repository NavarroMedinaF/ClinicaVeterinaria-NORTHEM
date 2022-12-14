package com.mindhub.finalProject.repository;

import com.mindhub.finalProject.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
