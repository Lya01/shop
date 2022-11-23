package com.example.shop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data

@ToString
@Table(name="prodotti_in_cart")
public class ProdottoInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Basic
    @Column(name="disponibilita",nullable = true)
    private int disponibilita;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="cart")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="prodotti")
    private Prodotto prodotti;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof ProdottoInCart)) return false;
        ProdottoInCart c = (ProdottoInCart) o;
        return c.id==id;
    }



}
