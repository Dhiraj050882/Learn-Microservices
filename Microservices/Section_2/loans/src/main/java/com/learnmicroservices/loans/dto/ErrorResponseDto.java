package com.learnmicroservices.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "Error Response", description = "Schema to hold Error Response Status Code and Message")
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "HTTP Status code of error received in response"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Http Status error message received in response"
    )
    private String errorMessage;
    @Schema(
            description = "Time of the Error Occurred"
    )
    private LocalDateTime errorTime;
}
