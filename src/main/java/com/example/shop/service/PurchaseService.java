package com.example.shop.service;

import com.example.shop.entity.*;
import com.example.shop.exceptions.QuantitaNegativa;
import com.example.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProdottoInPurchaseRepository prodottoInPurchaseRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;

    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Purchase addPurchase(Purchase purchase) throws QuantitaNegativa {
        Purchase newPurchase = purchaseRepository.save(purchase);
        for ( ProdottoInPurchase prodottiAcquistati : newPurchase.getProductsInPurchase() ) {
            prodottiAcquistati.setPurchase(newPurchase);
            ProdottoInPurchase justAdded = prodottoInPurchaseRepository.save(prodottiAcquistati);
            entityManager.refresh(justAdded);

            Prodotto clothing = justAdded.getProdotto();
            int newQuantity = clothing.getDisponibilita() - prodottiAcquistati.getQuantita();

            if ( newQuantity < 0 ) {
                throw new QuantitaNegativa();
            }
            clothing.setDisponibilita(newQuantity);
            entityManager.refresh(prodottiAcquistati);
        }
        entityManager.refresh(newPurchase);
        return newPurchase;

}
    @Transactional(readOnly = false, rollbackFor = IllegalArgumentException.class)
    public ProdottoInPurchase addPurchase(Prodotto clothing, String email, int qta) throws IllegalArgumentException {
        Customer utente = customerRepository.findByEmail(email);
        Purchase purchase = new Purchase();
        purchase.setCustomer(utente);

        ProdottoInPurchase clothingInPurchase = new ProdottoInPurchase();
        clothingInPurchase.setProdotto(clothing);
        clothingInPurchase.setPurchase(purchase);
        clothingInPurchase.setQuantita(qta);
        ProdottoInPurchase clothingInPurchaseNew = prodottoInPurchaseRepository.save(clothingInPurchase);

        purchase.getProductsInPurchase().add(clothingInPurchaseNew);//lo aggiungo nella lista dei miei acquisti
        Purchase purchaseNew = purchaseRepository.save(purchase);
        utente.getPurchases().add(purchaseNew);
        customerRepository.save(utente);

        Prodotto clo = prodottoRepository.findById(clothing.getIdProdotto()).get();//mi prendo il prodotto, se il prezzo è diverso allora l'ha modificato e non posso fare l'acquisto
        clo.setDisponibilita(clothing.getDisponibilita() - qta);//con il rollback for annullavo tutti i save fatti prima
        if (clo.getPrezzo() != clothing.getPrezzo())
            throw new IllegalArgumentException();
        return clothingInPurchaseNew;
    }

    @Transactional(readOnly = true)
    public List<ProdottoInPurchase> getPurchasesByUser(String email){
        List<Purchase> purchases = customerRepository.findByEmail(email).getPurchases();
        List<ProdottoInPurchase> ret=new ArrayList<>();
        for(Purchase p : purchases){
            for(ProdottoInPurchase cp : p.getProductsInPurchase()){
                ret.add(cp);
            }
        }
        return ret;
}

}
