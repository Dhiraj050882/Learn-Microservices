package com.learnmicroservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API Path invoked by client", example = "/AccountApi/update"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error code"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error Message representing the error message returned"
    )
    private String errorMessage;

    @Schema(
            description = "Error Time representing the time when error occurred"
    )
    private LocalDateTime errorTime;
}
