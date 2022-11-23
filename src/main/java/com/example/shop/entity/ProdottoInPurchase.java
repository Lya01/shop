package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name="prodotto_in_purchase")
public class ProdottoInPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "quantita", nullable = true)
    private int quantita;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "prodotti")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "purchase")
    @ToString.Exclude
    @JsonIgnore
    private Purchase purchase;
}
