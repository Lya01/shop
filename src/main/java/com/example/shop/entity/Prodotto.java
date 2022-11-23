package com.example.shop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private int idProdotto;

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

    @NotNull
    @Column(name="disponibilita")
    private int disponibilita;

    @OneToMany(targetEntity = ProdottoInCart.class, mappedBy="prodotti",cascade= CascadeType.MERGE)
    @ToString.Exclude
    @JsonIgnore
    private List<ProdottoInCart> prodottoInCart;

    public String toString(){
        return "prodotto "+idProdotto+", "+disponibilita+" "+nome;
    }
}
