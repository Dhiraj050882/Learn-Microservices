package com.learnmicroservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "This schema holds Accounts Information"
)
public class AccountsDto {

    @Schema(
            description = "Account number of dummy bank", example = "1432678906"
    )
    @NotEmpty(message = "Account number can't be Empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message="Account number must be a 10 digit number")
    private long accountNumber;

    @Schema(
            description = "Type of the account", example = "Savings"
    )
    @NotEmpty(message = "Account type value can't be invalid or Empty")
    private String accountType;

    @Schema(
            description = "Address of the branch of the bank where account was opened",
            example = "dummy bank Kishangunj"
    )
    @NotEmpty(message = "Branch Address can't be invalid or Empty")
    private String branchAddress;

}
