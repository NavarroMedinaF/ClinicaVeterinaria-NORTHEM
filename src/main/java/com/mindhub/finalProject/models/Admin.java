package com.mindhub.finalProject.models;

import javax.persistence.Entity;

@Entity
public class Admin extends Usuario{
    public Admin() {
    }

    public Admin(long dni, String email, String password,
                 Authority authority) {
        super(dni, email, password, authority);
    }
}
