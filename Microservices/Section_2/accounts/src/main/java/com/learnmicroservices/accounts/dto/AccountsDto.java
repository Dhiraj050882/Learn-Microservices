package com.learnmicroservices.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number can't be Empty")
    @Pattern(regexp = "(^$|[0-9{10}])",message="Account number must be a 10 digit number")
    private long accountNumber;

    @NotEmpty(message = "Account type value can't be invalid or Empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can't be invalid or Empty")
    private String branchAddress;

}
