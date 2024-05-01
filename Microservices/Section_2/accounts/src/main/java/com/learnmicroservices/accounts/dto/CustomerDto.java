package com.learnmicroservices.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {

    @NotNull(message = "Name cannot be Null or Empty")
    @Size(min = 6,max=30,message = "Please enter minimum 6 characters or maximum 30 characters as your Name")
    private String name;

    @NotNull(message = "Email cannot be null or empty")
    @Email(message="Please enter a valid email ID")
    private String email;

    @NotEmpty(message = "Mobile number can't be Empty")
    @Pattern(regexp = "(^$|[0-9{10}])",message="Mobile number must be a 10 digit number")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
