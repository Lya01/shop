package com.example.shop.repository;

import com.example.shop.entity.Cart;

import com.example.shop.entity.Customer;
import com.example.shop.entity.ProdottoInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByCustomer(Customer customer);
    boolean existsCartByCustomer(Customer customer);
    boolean existsByProdottiInCart(ProdottoInCart prodottoInCart);
}
