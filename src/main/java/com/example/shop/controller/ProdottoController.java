package com.example.shop.controller;

import com.example.shop.service.ProdottoService;
import com.example.shop.entity.Prodotto;
import com.example.shop.handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addProdotto(@RequestBody @Valid Prodotto prodotto) {
        try {
            Prodotto addedProdotto = prodottoService.addProdotto(prodotto);
            return ResponseHandler.handleResponse("Successfully add product", HttpStatus.OK, addedProdotto);

        } catch (Exception e) {
            return ResponseHandler.handleResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PutMapping(path = "/edit") //modificare un prodotto già inserito
    public ResponseEntity<Object> editProdotto(@RequestBody @Valid Prodotto prodotto) {
        try {
            Prodotto editedProdotto = prodottoService.editProdotto(prodotto);
            if (editedProdotto != null) {
                return ResponseHandler.handleResponse("Successfully edit product", HttpStatus.OK, editedProdotto);

            } else {
                return ResponseHandler.handleResponse("Product Id not exist", HttpStatus.BAD_REQUEST, null);

            }

        } catch (Exception e) {
            return ResponseHandler.handleResponse("Error", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteProdotto(@PathVariable Integer id) {
        try {
            prodottoService.deleteProdotto(id);
            return ResponseHandler.handleResponse("Successfully delete product", HttpStatus.OK, null);

        } catch (Exception e) {
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(path = "/pages")
    public ResponseEntity<Object> getProdotti(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "5") int limit,
                                              @RequestParam(required = false) String productName,
                                              @RequestParam(required = false) Sort.Direction sortType) {
        try {
            Page<Prodotto> prodottoPage = prodottoService.getRequestFilters(page, limit, productName, sortType);
            return ResponseHandler.handleResponse("Successfully get products", HttpStatus.OK, prodottoPage);
        } catch (Exception e) {
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(path="/get")
    public List<Prodotto> getProdotti(@Param(value = "nome") String productName) {
           // System.out.println("ciaooooooooooooo");
            List<Prodotto> prodotti = prodottoService.getProdotti(productName);
           // System.out.println("pr= "+prodotti.toString());

            return prodotti;

    }



}
