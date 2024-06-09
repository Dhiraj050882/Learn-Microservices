package com.learnrest.rest_web_services.service.impl;

import com.learnrest.rest_web_services.service.IHelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class HelloWorld implements IHelloWorld {

    public static Logger log = LoggerFactory.getLogger(HelloWorld.class);

    @Override
    public ResponseEntity<String> getMessage(WebRequest request) {
        String strHeaderContent = request.getHeader("Content-Type");
        log.debug("Header Name for Content Type is: {}",strHeaderContent);

        final HttpHeaders headers = new HttpHeaders();

        if(strHeaderContent.equals(MediaType.APPLICATION_XML_VALUE)){
            headers.setContentType(MediaType.APPLICATION_XML);
        }else{
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        return new ResponseEntity<>("Hello World",headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HelloWorldBean> getMessageBean(WebRequest request) {
        final String strHeaderContent = request.getHeader("Content-Type");
        log.debug("Header Name for Content Type in Bean Method is: {}",strHeaderContent);

        final HttpHeaders headers = new HttpHeaders();

        assert strHeaderContent != null;
        if(strHeaderContent.equals(MediaType.APPLICATION_XML_VALUE)){
            headers.setContentType(MediaType.APPLICATION_XML);
        }else{
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        return new ResponseEntity<>(new HelloWorldBean("Hello World!!"),headers, HttpStatus.OK);
    }


}
