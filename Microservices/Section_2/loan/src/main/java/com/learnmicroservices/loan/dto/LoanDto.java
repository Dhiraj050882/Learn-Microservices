package com.learnmicroservices.loan.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoanDto {

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private long totalLoan;

    private long amountPaid;

    private long outstandingAmount;
}
