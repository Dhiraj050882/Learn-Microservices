package com.learnmicroservices.accounts.service;

import com.learnmicroservices.accounts.dto.CustomerDto;

public interface IAccountsService {

    void CreateAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
