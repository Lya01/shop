package com.example.shop.controller;


import com.example.shop.service.ProdottoInCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodottoInCarrello")
public class ProdottoInCartController {

    @Autowired
    private ProdottoInCartService prodottoInCartService;
}
