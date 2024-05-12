package com.learnmicroservices.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response Data Transfer Object", description = "Schema to hold successful response information")
@Data @AllArgsConstructor
public class ResponseDto {

    @Schema(description = "Status code of the Response")
    private String StatusCode;

    @Schema(description = "Response Status Message")
    private String StatusMessage;

}
