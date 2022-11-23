package com.example.shop.service;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Customer;
import com.example.shop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustumerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartService cartService;

    @Transactional(readOnly = true)
    public Customer login(String email, String password){
        System.out.println("sono in login del service");
        //controllo null
        return customerRepository.findByEmailAndPassword(email,password);
    }

    @Transactional
    public Customer registration(Customer customer){
        Customer savedCustomer=customerRepository.save(customer);
        Cart savedCart=cartService.create(savedCustomer);
        savedCustomer.setCart(savedCart);
        System.out.println("sono in registration del service ");
        return savedCustomer;
    }
}
