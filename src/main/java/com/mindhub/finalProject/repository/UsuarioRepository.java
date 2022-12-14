package com.mindhub.finalProject.repository;

import com.mindhub.finalProject.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUserByEmail(String number);
}
