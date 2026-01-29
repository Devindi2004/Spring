package org.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @GetMapping("index1")
    public String index1() {
        return "Hello World";
    }
    @GetMapping("index2")
    public String index2() {
        return "Hello index2";
    }
    @GetMapping("index3")
    public String index3() {
        return "Hello index3";
    }
    @PostMapping("post")
    public String post() {
        return "Hello post";
    }
    @DeleteMapping("delete")
    public String delete() {
        return "Hello delete";
    }
    @PutMapping("put")
    public String put() {
        return "Hello put";
    }
    @PatchMapping("patch")
    public String patch() {
        return "Hello patch";
    }
}
