package com.example.shop.service;

import com.example.shop.entity.*;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.ProdottoInCartRepository;
import com.example.shop.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProdottoInCartRepository prodottoInCartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional(readOnly = true)
    public List<ProdottoInCart> showAllProdotti(Customer customer) {
        if (cartRepository.existsCartByCustomer(customer)) {
            Cart cart = cartRepository.findCartByCustomer(customer);
            return cart.getProdottiInCart();
        }
        return new ArrayList<>();
    }

    @Transactional //di default readOnly è a false
    public ProdottoInCart addProduct(String email, Prodotto prodotto) {
        Customer utente = customerRepository.findByEmail(email);
        System.out.println(utente);
        Cart carrello = utente.getCart();
        ProdottoInCart prodottoInCart = null;
        for (ProdottoInCart cc : carrello.getProdottiInCart()) {
            if (cc.getProdotti().getIdProdotto() == prodotto.getIdProdotto()) {
                prodottoInCart = cc;
                break;
            }
        }


        if (prodottoInCart != null) {
            //Nel carrello esiste gia' un carrello prodotto contenente il prodotto
            prodottoInCart.setDisponibilita(prodottoInCart.getDisponibilita() + 1);
            ProdottoInCart carrelloClothingNew = prodottoInCartRepository.save(prodottoInCart);
            carrello.getProdottiInCart().remove(prodottoInCart);//rimuovo quello con quantità sbagliata
            carrello.getProdottiInCart().add(carrelloClothingNew);
            cartRepository.save(carrello);
            return carrelloClothingNew;
        } else {
            prodottoInCart = new ProdottoInCart();
            prodottoInCart.setCart(carrello);
            prodottoInCart.setProdotti(prodotto);
            prodottoInCart.setDisponibilita(1);
            ProdottoInCart carrelloClothingNew = prodottoInCartRepository.save(prodottoInCart);
            carrello.getProdottiInCart().add(carrelloClothingNew);
            cartRepository.save(carrello);
            return carrelloClothingNew;
        }
    }


    @Transactional
    public ProdottoInCart removeClothing(String email, Prodotto prodotto) {
        Cart carrello = customerRepository.findByEmail(email).getCart();
        for (ProdottoInCart carrelloClothing : carrello.getProdottiInCart()) {
            if (carrelloClothing.getProdotti().getIdProdotto() == prodotto.getIdProdotto()) {
                carrello.getProdottiInCart().remove(carrelloClothing);
                prodottoInCartRepository.delete(carrelloClothing);
                cartRepository.save(carrello);
                return carrelloClothing;
            }
        }
        //Non verra' mai eseguito
        return null;
    }


    @Transactional
    public Cart create(Customer customer) {
        Cart c = new Cart();
        c.setCustomer(customer);
        return cartRepository.save(c);
    }
}