package com.example.shop.repository;

import com.example.shop.entity.Prodotto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Integer>{
    Page<Prodotto> findByNomeContainingIgnoreCase(String productName, Pageable pageable);
    @Query(value = "SELECT * "+
    "FROM prodotti pr "+
    "WHERE pr.nome LIKE :nome OR :nome IS NULL",nativeQuery = true)
    List<Prodotto> findByNome(String nome);
}
