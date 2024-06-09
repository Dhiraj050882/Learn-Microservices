package com.learnrest.rest_web_services.controller;

import com.learnrest.rest_web_services.service.impl.HelloWorldBean;
import com.learnrest.rest_web_services.service.IHelloWorld;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(path = "api",consumes = {MediaType.ALL_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class HelloworldController {

    private final IHelloWorld helloWorld;

    public HelloworldController(IHelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<String> helloWord(WebRequest request){
        return helloWorld.getMessage(request);
    }

    @GetMapping("/hello-world-bean")
    public ResponseEntity<HelloWorldBean> helloWordBean(WebRequest request){
        return helloWorld.getMessageBean(request);
    }
}
