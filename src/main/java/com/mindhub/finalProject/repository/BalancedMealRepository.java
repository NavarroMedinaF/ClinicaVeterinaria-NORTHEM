package com.mindhub.finalProject.repository;

import com.mindhub.finalProject.models.BalancedMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BalancedMealRepository extends JpaRepository<BalancedMeal,Long> {
}
