package com.example.shop.repository;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Customer;
import com.example.shop.entity.Prodotto;
import com.example.shop.entity.ProdottoInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoInCartRepository extends JpaRepository<ProdottoInCart, Integer> {

    ProdottoInCart findByCart(Cart cart);
    ProdottoInCart findByProdotti(Prodotto prodotto);
    boolean existsByProdotti(Prodotto prodotto);
}
