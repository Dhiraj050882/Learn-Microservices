package com.learnmicroservices.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders requestHeader = exchange.getRequest().getHeaders();

        if(isCorrelationIdPresent(requestHeader)){
            log.debug("demoBank-correlation-id found in RequestTraceFilter : {}",
                    filterUtility.getCorrelationId(requestHeader));
        }else{
            String strCorrelationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange,strCorrelationId);

            log.debug("demoBank-correlation-id generated in RequestTraceFilter : {}",
                    strCorrelationId);
        }

        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders headers){

        return filterUtility.getCorrelationId(headers) != null;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
