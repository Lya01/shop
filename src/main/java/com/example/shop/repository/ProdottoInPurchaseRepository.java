package com.example.shop.repository;

import com.example.shop.entity.ProdottoInPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoInPurchaseRepository extends JpaRepository<ProdottoInPurchase,Integer> {
}
