package com.mindhub.finalProject.Services.repository;

import com.mindhub.finalProject.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String number);
}
