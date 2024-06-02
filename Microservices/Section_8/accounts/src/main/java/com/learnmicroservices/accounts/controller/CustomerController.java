package com.learnmicroservices.accounts.controller;

import com.learnmicroservices.accounts.dto.CustomerDetailsDto;
import com.learnmicroservices.accounts.dto.ErrorResponseDto;
import com.learnmicroservices.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "REST API for Customers in Indian Bank",
        description = "API for fetching customers information"
)
@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/fetchCustomerDetails")
    @Operation(
            summary     = "Fetch user Customer API",
            description = "REST API to fetch customer Detailed information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description  = "HTTPS Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description  = "HTTPS Status Internal Server Error",
                    content = @Content(schema = @Schema(
                            implementation = ErrorResponseDto.class
                    ))
            )
    })
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile number must be a 10 digit number")
                                                                   String mobileNumber){

        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);
    }
}
