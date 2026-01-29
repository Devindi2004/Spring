package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// says it is a controller->when comes a request return to ui
@RequestMapping(path = "hello")//map url for controller->localhost:8080/webappexploded/hello
public class HelloController {
    public HelloController() {
        System.out.println("HelloController constructor");
    }
    @GetMapping//get the request
    public String hello() {
        return "hello";
    }
    @GetMapping(path = "index")
    public String index() {
        return "index";
    }
}