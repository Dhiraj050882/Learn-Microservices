package com.learnmicroservices.loan.repository;

import com.learnmicroservices.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,Long> {


    Optional<Loan> findByLoanId(long loanId);

    @Transactional
    @Modifying
    void deleteByLoanId(long loanId);
}
