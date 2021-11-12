package com.example.webService.helloworld;

import com.example.webService.user.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

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






}
