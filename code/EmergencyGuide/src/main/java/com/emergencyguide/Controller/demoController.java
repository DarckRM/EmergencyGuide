package com.emergencyguide.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello";
    }

}
