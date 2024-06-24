package com.learnsecurity.banksecurity.controller;

import com.learnsecurity.banksecurity.model.Accounts;
import com.learnsecurity.banksecurity.repository.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam int id) {
        log.info("Id passed to AccountController '/myAccount' is {}",id);
        return accountsRepository.findByCustomerId(id);
    }
}
