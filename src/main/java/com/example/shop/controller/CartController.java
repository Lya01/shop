package com.example.shop.controller;

import com.example.shop.entity.Customer;
import com.example.shop.entity.Prodotto;
import com.example.shop.entity.ProdottoInCart;
import com.example.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping(path="/showAll")
    public List<ProdottoInCart> showAllProdotti(@RequestBody Customer utente){
        return cartService.showAllProdotti(utente);
    }

    @PutMapping(path="/add")
    public ProdottoInCart addProdotto(@Param(value="email") String email, @RequestBody @Valid Prodotto product){
        System.out.println(product.toString());
        System.out.println("Medoto add clothing in cart");
        return cartService.addProduct(email,product);
    }

    @PutMapping(path="/remove")
    public ProdottoInCart removeClothing(@Param(value="email") String email, @RequestBody Prodotto clothing){
        System.out.println("Metodo remove clothing in cart");
        return cartService.removeClothing(email,clothing);
    }

}
