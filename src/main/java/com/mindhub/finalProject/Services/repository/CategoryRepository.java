package com.mindhub.finalProject.Services.repository;

import com.mindhub.finalProject.models.Admin;
import com.mindhub.finalProject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
