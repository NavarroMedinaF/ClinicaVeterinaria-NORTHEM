package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    private long dni;

    private String email, password;

    private Authority authority;

    public Usuario() {
    }

    public Usuario(long dni, String email, String password,
                   Authority authority) {
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    /* GETTERS */

    public long getId() {
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

    /* SETTERS */

    public void setDni(long dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
