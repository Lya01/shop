package com.example.shop.controller;

import com.example.shop.entity.Customer;
import com.example.shop.handler.ResponseHandler;
import com.example.shop.service.CustumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustumerService customerService;

    @GetMapping(path="/login")
    public Customer login(@RequestParam (value="email") String email,
                          @RequestParam (value="password") String password) {
        /*System.out.println("Sono in login del controller");
        System.out.println(email);
        System.out.println(password);*/
        Customer c=customerService.login(email, password);
        return c;
    }

    @PostMapping(path="/registration")
    public ResponseEntity<Object> registration(@RequestBody Customer customer){
        try {
           /* System.out.println(customer.toString());
            System.out.println("sono in registration");*/
            Customer addedCustomer = customerService.registration(customer);
            //System.out.println(addedCustomer);
            return ResponseHandler.handleResponse("Successfully add customer", HttpStatus.OK, addedCustomer);
        }catch(Exception e){
           // System.out.println(e);
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
