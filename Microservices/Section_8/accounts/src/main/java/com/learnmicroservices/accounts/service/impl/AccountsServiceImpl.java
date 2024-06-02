package com.learnmicroservices.accounts.service.impl;

import com.learnmicroservices.accounts.constants.AccountsConstants;
import com.learnmicroservices.accounts.dto.AccountsDto;
import com.learnmicroservices.accounts.dto.CustomerDto;
import com.learnmicroservices.accounts.entity.Accounts;
import com.learnmicroservices.accounts.entity.Customer;
import com.learnmicroservices.accounts.exception.CustomerAlreadyExistsException;
import com.learnmicroservices.accounts.exception.ResourceNotFoundException;
import com.learnmicroservices.accounts.mapper.AccountsMapper;
import com.learnmicroservices.accounts.mapper.CustomerMapper;
import com.learnmicroservices.accounts.repository.AccountsRepository;
import com.learnmicroservices.accounts.repository.CustomerRepository;
import com.learnmicroservices.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@ToString(callSuper=true, includeFieldNames=true)
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void CreateAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerbyNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerbyNumber.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with mobile number "+customerDto.getMobileNumber()
                    +" already Registered.");
        }


        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber",mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account","CustomerId", String.valueOf(customer.getCustomerId()))
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account","AccountNumber",Long.toString(accountsDto.getAccountNumber()))
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            long customerID = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerID).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","Customer ID",Long.toString(customerID))
            );

            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);

            isUpdated = true;

        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobile Number",mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

}
