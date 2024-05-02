package com.learnmicroservices.loan.controller;

import com.learnmicroservices.loan.service.ILoanService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    private ILoanService iLoanService;

    @PostMapping("/create")
    public String createLoan(){
        return "HelloWorld";
    }
}
