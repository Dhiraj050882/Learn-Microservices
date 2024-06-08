package com.learnmicroservices.accounts.service;

import com.learnmicroservices.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String strCorrelationID);
}
