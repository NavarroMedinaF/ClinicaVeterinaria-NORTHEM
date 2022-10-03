package com.mindhub.finalProject.models;

import org.apache.catalina.LifecycleState;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Prepaid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long associateNumber;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    /*CONTRUCTOR*/
    public Prepaid() {
    }

    public Prepaid(Client client, Category category) {
        this.client = client;
        this.category = category;
    }

    /* GETTERS */

    public long getAssociateNumber() {
        return associateNumber;
    }

    public Client getClient() {
        return client;
    }

    public Category getCategory() {
        return category;
    }

    /* SETTERS */

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
