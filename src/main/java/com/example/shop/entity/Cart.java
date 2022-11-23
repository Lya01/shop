package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
@Data
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name="customer")
    private Customer customer;

    @OneToMany(targetEntity = ProdottoInCart.class, mappedBy = "cart",cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JsonIgnore
    private List<ProdottoInCart> prodottiInCart=new ArrayList<>();

}
