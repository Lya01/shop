package com.example.shop.repository;

import com.example.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailAndPassword(String email,String password);

    Customer findByEmail(String email);
}

