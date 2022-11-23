package com.example.shop.repository;

import com.example.shop.entity.Customer;
import com.example.shop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByCustomer(Customer customer);
    List<Purchase> findByPurchaseTime(Date data);

    //@Query("select p from Purchase p where p.purchaseTime > ?1 and p.purchaseTime < ?2 and p.buyer = ?3" )
    //List<Purchase> findByCustomerInPeriod(Date start, Date end, Customer utente);
}
