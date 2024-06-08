package com.learnmicroservices.accounts.service.client;

import com.learnmicroservices.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/cardsApi/fetch", consumes = "application/json")
    ResponseEntity<CardsDto> fetchCardInfo(@RequestHeader("demobank-correlation-id") String strCorrelationId, @RequestParam String mobileNumber);

}
