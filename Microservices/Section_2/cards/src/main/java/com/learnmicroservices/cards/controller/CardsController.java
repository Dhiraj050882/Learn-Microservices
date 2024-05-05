package com.learnmicroservices.cards.controller;

import com.learnmicroservices.cards.constant.CardsConstants;
import com.learnmicroservices.cards.dto.CardsDto;
import com.learnmicroservices.cards.dto.ErrorResponseDto;
import com.learnmicroservices.cards.dto.ResponseDto;
import com.learnmicroservices.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Cards in Dummy Bank",
        description = "CRUD REST APIs in Dummy Bank to CREATE, UPDATE, FETCH AND DELETE Cards details"
)
@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "/cardsApi",produces = MediaType.APPLICATION_JSON_VALUE)
public class CardsController {

    private ICardsService iCardsService;

    @Operation(
            summary = "Create Cards REST API",
            description = "REST API to create new Card inside Dummy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                    schema = @Schema(
                            implementation = ErrorResponseDto.class
                    )
            ))
    })
    @RequestMapping(path = "/create")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digit number")
            String mobileNumber){
        iCardsService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201)
        );
    }

    @Operation(
            summary = "Fetch Cards REST API",
            description = "REST API to fetch new Card inside Dummy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    ))
    })
    @RequestMapping(path = "/fetch")
    public ResponseEntity<CardsDto> fetchCardInfo(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digit")
            String mobileNumber){

        CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);

    }


    @Operation(
            summary = "Update Cards Details REST API",
            description = "REST API to update cards details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @RequestMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateCardsInfo(@RequestBody CardsDto cardsDto){

        boolean isUpdated = iCardsService.updateCard(cardsDto);

        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(CardsConstants.MESSAGE_200,CardsConstants.MESSAGE_200)
            );
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE)
            );
        }

    }

    @Operation(
            summary = "Delete Cards Details REST API",
            description = "REST API to Cards Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @RequestMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteCardInfo(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digit")
            String mobileNumber){
        boolean isDeleted = iCardsService.deleteCards(mobileNumber);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200)
            );
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE)
            );
        }
    }



}
