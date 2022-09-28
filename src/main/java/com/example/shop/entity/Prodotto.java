package com.example.shop.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="prodotti")
public class Prodotto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_prodotto")
    private Integer idProdotto;

    @NotNull
    @Column(name="nome")
    private String nome;

    @NotNull
    @Column(name="descrizione")
    private String descrizione;

    @NotNull
    @Column(name="image")
    private String image;

    @NotNull
    @Column(name="prezzo")
    private double prezzo;
}
