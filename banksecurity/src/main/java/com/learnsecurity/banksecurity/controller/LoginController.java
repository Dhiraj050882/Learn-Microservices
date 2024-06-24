package com.learnsecurity.banksecurity.controller;

import com.learnsecurity.banksecurity.model.Customer;
import com.learnsecurity.banksecurity.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer=null;
        ResponseEntity responseEntity=null;
        try{
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            log.info("date to be set for customer is {}",customer.getCreateDt());
            log.info("role to be set for customer is {}",customer.getRole());
            log.info("pwd to be set for customer is {}",customer.getPwd());
            log.info("id to be set for customer is {}",customer.getId());
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0){
                responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                        .body("User successfully registered");
            }
        }catch(Exception ex){
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to "+ex.getMessage());
        }

        return responseEntity;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if (!customers.isEmpty()) {
            return customers.get(0);
        } else {
            return null;
        }

    }

}
