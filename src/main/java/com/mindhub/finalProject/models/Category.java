package com.mindhub.finalProject.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ElementCollection
    private Set<Prepaid> prepaids = new HashSet<>();

    private String name;

    private double price, discount;


    public Category() {
    }

    public Category(double price, double discount, String name) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public Set<Prepaid> getPrepaids() {
        return prepaids;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    /*SETTERS*/

    public void setPrepaids(Set<Prepaid> prepaids) {
        this.prepaids = prepaids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
