package com.learnrestapi.restfulapi.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldController {

    @GetMapping(path = "hello-world")
    public HelloWorld helloWorld(){
        return new HelloWorld("Hello World");
    }


    @GetMapping(path = "hello-world/path-varialble/{name}")
    public HelloWorld HelloWorldVariable(@PathVariable String name){
        return new HelloWorld("Hello World, "+name);
    }
}
