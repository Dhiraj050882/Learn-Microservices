package com.learnmicroservices.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;


@Configuration
public class ResponseTraceFilter {

    public static final Logger log = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter(){
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                HttpHeaders requestHeader = exchange.getRequest().getHeaders();
                String strCorrelationId = filterUtility.getCorrelationId(requestHeader);
                log.debug("Updated the correlation id to the outbound headers: {}",strCorrelationId);
                exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, strCorrelationId);
            }));
        };
    }
}
