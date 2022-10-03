package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Authority;
import com.mindhub.finalProject.models.Usuario;

public class UserDTO {
    private long dni;

    private String email, password;

    private Authority authority;

    public UserDTO(Usuario usuario) {
        this.dni = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.authority = usuario.getAuthority();
    }

    /*GETTERS*/
    public long getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Authority getAuthority() {
        return authority;
    }
}
