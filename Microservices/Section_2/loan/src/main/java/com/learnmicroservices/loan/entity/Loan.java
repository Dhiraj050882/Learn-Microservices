package com.learnmicroservices.loan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Loan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private long  loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private long totalLoan;

    @Column(name = "amount_paid")
    private long amountPaid;

    @Column(name = "outstanding_amount")
    private long outstandingAmount;
}
