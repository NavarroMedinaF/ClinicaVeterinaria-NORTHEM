package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_dni")
    private Client client;

    @ElementCollection
    @Column(name = "products")
    private List<Product> products;

    private double amount;

    private LocalDateTime datePurchase;

    /*CONTRUCTOR*/

    public Purchase() {
    }

    public Purchase(Client client, double amount, LocalDateTime datePurchase) {
        this.client = client;
        this.amount = amount;
        this.datePurchase = datePurchase;
    }

    //* GETTERS

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDatePurchase() {
        return datePurchase;
    }

    //* SETTERS

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDatePurchase(LocalDateTime datePurchase) {
        this.datePurchase = datePurchase;
    }
}
