package com.example.webService.helloworld;

import com.example.webService.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    @Autowired
    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    //GET
    // /hello-world -> (endpoint)
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    //alt + enter
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }


    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language" , required = false) Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);
    }






}
