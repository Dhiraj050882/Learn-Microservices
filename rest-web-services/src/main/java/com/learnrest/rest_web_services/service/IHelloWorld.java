package com.learnrest.rest_web_services.service;


import com.learnrest.rest_web_services.service.impl.HelloWorldBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface IHelloWorld {

    ResponseEntity<String> getMessage(WebRequest request);

    ResponseEntity<HelloWorldBean> getMessageBean(WebRequest request);

}
