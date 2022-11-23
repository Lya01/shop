package com.example.shop.controller;

import com.example.shop.entity.Prodotto;
import com.example.shop.entity.ProdottoInPurchase;
import com.example.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PutMapping("/p")
    public ProdottoInPurchase addPurchase(@RequestBody Prodotto clothing, @Param(value="email") String email,
                                          @Param(value="qta") int qta){
        System.out.println("Metodo addPurchase");
        return purchaseService.addPurchase(clothing, email, qta);
    }

    @GetMapping("/purchases")
    public List<ProdottoInPurchase> getPurchasesByCustomer(@Param(value="email") String email){
        return purchaseService.getPurchasesByUser(email);
    }


}
