package com.example.shop.repository;

import com.example.shop.entity.Prodotto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Integer>{
    Page<Prodotto> findByNomeContainingIgnoreCase(String productName, Pageable pageable);
}
