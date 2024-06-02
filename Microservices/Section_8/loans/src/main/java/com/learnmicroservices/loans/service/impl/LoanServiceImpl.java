package com.learnmicroservices.loans.service.impl;

import com.learnmicroservices.loans.constant.LoansConstants;
import com.learnmicroservices.loans.dto.LoansDto;
import com.learnmicroservices.loans.entity.Loans;
import com.learnmicroservices.loans.exception.LoanAlreadyExistsException;
import com.learnmicroservices.loans.exception.ResourceNotFoundException;
import com.learnmicroservices.loans.mapper.LoansMapper;
import com.learnmicroservices.loans.repository.LoanRepository;
import com.learnmicroservices.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    LoanRepository loanRepository;
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }

        Loans loans = new Loans();

        long accountNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(accountNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        loanRepository.save(loans);
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber));
        return LoansMapper.mapToLoansDto(new LoansDto(),loans);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan = loanRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan","Loan Number",loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loan,loansDto);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber)
        );
        loanRepository.delete(loans);
        return true;
    }
}
