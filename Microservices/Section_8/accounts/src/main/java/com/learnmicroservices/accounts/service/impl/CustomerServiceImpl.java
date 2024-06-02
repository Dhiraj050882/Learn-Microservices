package com.learnmicroservices.accounts.service.impl;

import com.learnmicroservices.accounts.dto.AccountsDto;
import com.learnmicroservices.accounts.dto.CardsDto;
import com.learnmicroservices.accounts.dto.CustomerDetailsDto;
import com.learnmicroservices.accounts.dto.LoansDto;
import com.learnmicroservices.accounts.entity.Accounts;
import com.learnmicroservices.accounts.entity.Customer;
import com.learnmicroservices.accounts.exception.ResourceNotFoundException;
import com.learnmicroservices.accounts.mapper.AccountsMapper;
import com.learnmicroservices.accounts.mapper.CustomerMapper;
import com.learnmicroservices.accounts.repository.AccountsRepository;
import com.learnmicroservices.accounts.repository.CustomerRepository;
import com.learnmicroservices.accounts.service.ICustomerService;
import com.learnmicroservices.accounts.service.client.CardsFeignClient;
import com.learnmicroservices.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber",mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account","CustomerId", String.valueOf(customer.getCustomerId()))
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper
                                                .mapToCustomerDetailsDto(customer,
                                                        new CustomerDetailsDto());

        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);

        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardInfo(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
