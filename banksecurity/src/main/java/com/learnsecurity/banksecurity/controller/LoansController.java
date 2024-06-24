package com.learnsecurity.banksecurity.controller;

import com.learnsecurity.banksecurity.model.Loans;
import com.learnsecurity.banksecurity.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam int id){
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
