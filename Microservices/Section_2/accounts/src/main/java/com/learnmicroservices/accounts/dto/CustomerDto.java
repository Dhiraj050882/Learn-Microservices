package com.learnmicroservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "This schema holds customer and accounts information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Dhiraj Singh"
    )
    @NotNull(message = "Name cannot be Null or Empty")
    @Size(min = 6,max=30,message = "Please enter minimum 6 characters or maximum 30 characters as your Name")
    private String name;

    @Schema(
            description = "Email ID of the customer", example = "dhirajsingh@dummybank.com"
    )
    @NotNull(message = "Email cannot be null or empty")
    @Email(message="Please enter a valid email ID")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "9007000435"
    )
    @NotEmpty(message = "Mobile number can't be Empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile number must be a 10 digit number")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;

}
