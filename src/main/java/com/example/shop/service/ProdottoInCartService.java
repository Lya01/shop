package com.example.shop.service;

import com.example.shop.entity.Prodotto;
import com.example.shop.entity.ProdottoInCart;
import com.example.shop.repository.ProdottoInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdottoInCartService {
    @Autowired
    private ProdottoInCartRepository prodottoInCartRepository;

    @Transactional(readOnly = false)
    public void addProdotto(Prodotto prodotto){
        ProdottoInCart carrelloClothing = prodottoInCartRepository.findByProdotti(prodotto);
        carrelloClothing.setDisponibilita(carrelloClothing.getDisponibilita()+1);
        prodottoInCartRepository.save(carrelloClothing);
    }

    @Transactional(readOnly = false)
    public void removeProdotto(Prodotto prodotto){
        ProdottoInCart carrelloClothing=prodottoInCartRepository.findByProdotti(prodotto);
        carrelloClothing.setDisponibilita(carrelloClothing.getDisponibilita()-1);
        if(carrelloClothing.getDisponibilita()==0)
            prodottoInCartRepository.delete(carrelloClothing);
        else
            prodottoInCartRepository.save(carrelloClothing);
  }
}
