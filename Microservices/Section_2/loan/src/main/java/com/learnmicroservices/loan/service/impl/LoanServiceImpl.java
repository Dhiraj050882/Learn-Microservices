package com.learnmicroservices.loan.service.impl;

import com.learnmicroservices.loan.constant.LoanConstant;
import com.learnmicroservices.loan.dto.LoanDto;
import com.learnmicroservices.loan.entity.Loan;
import com.learnmicroservices.loan.repository.ILoanRepository;
import com.learnmicroservices.loan.service.ILoanService;

import java.util.Random;

public class LoanServiceImpl implements ILoanService {

    ILoanRepository iLoanRepository;

    @Override
    public void createLoan(String mobileNumber) {

        //Loan loan = LoanMapper.mapToLoan(loanDto, new Loan());
        iLoanRepository.save(createNewLoan(mobileNumber));

    }

    private Loan createNewLoan(String mobileNumber) {
        Loan loan = new Loan();
        long randomLoanNum = 1000000000L + new Random().nextInt(900000000);
        loan.setMobileNumber(mobileNumber);
        loan.setLoanNumber(Long.toString(randomLoanNum));
        loan.setLoanType(LoanConstant.HOME_LOAN);
        loan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        return loan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        return false;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        return false;
    }
}
