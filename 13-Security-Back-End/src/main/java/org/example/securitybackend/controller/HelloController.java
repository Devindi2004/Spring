package org.example.securitybackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hello")
public class HelloController {

    @GetMapping("h1")
    @PreAuthorize("hasRole('USER')")
    private String hello() {
        return "Hello World1";
    }

    @GetMapping("h2")
    @PreAuthorize("hasRole('ADMIN')")
    private String hello2() {
        return "Hello World2";
    }
}