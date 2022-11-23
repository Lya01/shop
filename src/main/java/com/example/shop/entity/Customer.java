package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="cart")
    private Cart cart;

    @OneToMany(mappedBy="customer", cascade=CascadeType.MERGE)
    @JsonIgnore
    private List<Purchase> purchases;

    public Cart getCart() {
        return cart;
    }
}

