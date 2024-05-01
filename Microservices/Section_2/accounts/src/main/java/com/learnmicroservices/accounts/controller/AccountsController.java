package com.learnmicroservices.accounts.controller;

import com.learnmicroservices.accounts.constants.AccountsConstants;
import com.learnmicroservices.accounts.dto.CustomerDto;
import com.learnmicroservices.accounts.dto.ErrorResponseDto;
import com.learnmicroservices.accounts.dto.ResponseDto;
import com.learnmicroservices.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CURD REST API for accounts in Indian Bank",
        description = "API for creating, updating, fetching and deleting Accounts and customer information"
)
@RestController
@RequestMapping(path="/AccountsApi", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create user Account API",
            description = "REST API to create new customer & account for Dummy Bank"
    )
    @ApiResponse(
            responseCode = "201",
            description  = "HTTP Status created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountsService.CreateAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    @Operation(
            summary     = "Fetch user Account API",
            description = "REST API to fetch customer & account information"
    )
    @ApiResponse(
            responseCode = "200",
            description  = "HTTPS Status OK"
    )
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile number must be a 10 digit number")
                                                               String mobileNumber){

        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    @Operation(
            summary     = "Update user Account API",
            description = "REST API to update customer & account for Dummy Bank"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "417",
                description = "Expectation Failed",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status INTERNAL SERVER ERROR",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
        )
        )
    })
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){

        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE)
            );
        }
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Delete user Account API",
            description = "REST API to delete customer & account for Dummy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile number must be a 10 digit number")
                                                                String mobileNumber){

        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE)
            );
        }
    }

}
