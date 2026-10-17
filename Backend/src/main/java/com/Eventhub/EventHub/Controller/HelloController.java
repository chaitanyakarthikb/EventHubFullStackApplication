package com.Eventhub.EventHub.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloController {
    
    @GetMapping ("/hello")
    public static String sayHello(){
        return "Hello world, my name is chaitanya karthik";
    }
}
