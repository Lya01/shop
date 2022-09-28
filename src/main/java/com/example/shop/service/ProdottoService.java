package com.example.shop.service;

import com.example.shop.entity.Prodotto;
import com.example.shop.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.awt.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    public Prodotto addProdotto(Prodotto prodotto){
        return prodottoRepository.save(prodotto);
    }

    public Prodotto editProdotto(Prodotto prodotto){ //per modificare il prodotto già precedentemente inserito
        boolean exist=prodottoRepository.existsById(prodotto.getIdProdotto());
        if(exist){
            return prodottoRepository.save(prodotto);
        }
        return null;
    }

    public void deleteProdotto(Integer id){
        prodottoRepository.deleteById(id);
    }

    public Page<Prodotto> getRequestFilters(int page, int limit, String productName, Sort.Direction sortType){
        Page<Prodotto> prodottoPage=null;
        if(productName==null && sortType==null){
            prodottoPage =getProdottiLista(page,limit);
        }
        if(productName!=null && sortType==null){
            prodottoPage= findProductByName(page,limit,productName);
        }
        if(productName==null && sortType!=null){
            prodottoPage= getProdottiOrderByPrezzo(page,limit,sortType);
        }
        if(productName!=null && sortType!=null){
            prodottoPage=findProdottiByNameAndOrderByPrezzo(page,limit,productName,sortType);
        }
        return prodottoPage;
    }

    private Page<Prodotto> getProdottiOrderByPrezzo(int page, int limit, Sort.Direction sortType) {
        Sort sort=Sort.by(sortType,"prezzo");
        Pageable pageable= PageRequest.of(page,limit,sort);
        return prodottoRepository.findAll(pageable);
    }

    private Page<Prodotto> findProductByName(int page,int limit,String productName){
        Pageable pageable= PageRequest.of(page,limit);
        return prodottoRepository.findByNomeContainingIgnoreCase(productName,pageable);
    }

    private Page<Prodotto> getProdottiLista(int page,int limit){
        Pageable pageable= PageRequest.of(page,limit);
        return prodottoRepository.findAll(pageable);
    }

    private Page<Prodotto> findProdottiByNameAndOrderByPrezzo(int page,int limit,String productName,Sort.Direction sortType){
        Sort sort=Sort.by(sortType,"prezzo");
        Pageable pageable=PageRequest.of(page,limit,sort);
        return prodottoRepository.findByNomeContainingIgnoreCase(productName,pageable);
    }
}
