package com.example.shop.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="purchase")
@Data
@ToString
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="customer")
    @ToString.Exclude
    private Customer customer;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_time")
    private Date purchaseTime;

    @OneToMany(mappedBy="purchase",cascade=CascadeType.MERGE)
    private List<ProdottoInPurchase> productsInPurchase=new ArrayList<>();


    @Version
    private long version;
}//Purchase

