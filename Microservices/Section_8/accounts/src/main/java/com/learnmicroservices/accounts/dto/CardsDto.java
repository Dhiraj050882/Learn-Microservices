package com.learnmicroservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Schema(name = "Cards Schema", description = "Schema to hold Cards information")
@Data
public class CardsDto {

    @NotEmpty(message = "Mobile Number can't be empty or invalid")
    @Schema(name = "Mobile Number", description = "Mobile number of the customer",example = "9830232919")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be a 10 digit number")
    private String mobileNumber;

    @NotEmpty(message = "Card Number can't be empty or invalid")
    @Schema(name = "Card Number", description = "Card number of the customer",example = "570024316978")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must be a 12 digit number")
    private String cardNumber;

    @NotEmpty(message = "Card type cant be empty or invalid")
    @Schema(
            description = "Type of the card", example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(
            description = "Total amount used by a Customer", example = "100000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card", example = "100000"
    )
    private int availableAmount;

}
