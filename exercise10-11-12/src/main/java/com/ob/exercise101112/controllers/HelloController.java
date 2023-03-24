package com.ob.exercise101112.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping( "/api/hello" )
    public String HelloWorld (){
        return "Hello World";
    }

}
