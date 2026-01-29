package org.example.controller;

import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaTray;

@RequestMapping("api/v1/request")
@RestController
public class RequestController {
    @PostMapping
    public String post(@RequestHeader ("Authorization")String authorization,@RequestHeader ("tocken")String tocken) {
        return "Hello World 1"+authorization+" : Tocken :"+tocken;
    }

    @PostMapping("queryString")
    public String getQuaryStringParameters(@RequestParam ("name")String name,
                                           @RequestParam("address") String address,
                                           @RequestParam("id") String id){
        return "Hello World 2" + name + " : " + address + " : " + id;
    }
    @PostMapping("pathvariable/{name}")
    public String getPathVariable(@PathVariable("name")String name){
        return "Hello World 3" + name ;
    }
    @PostMapping("pathvariable/{name}/{address}")
    public String getPathVariable(@PathVariable("name")String name,
                                  @PathVariable("address")String address){
        return "Hello World 3" + name + " : " + address;
    }
    //request body - form data - not worked
    @PostMapping(value = "body/form")
    public String getRequestBody(@RequestParam ("id")String id,
                                 @RequestParam("name")String name){
        return "Hello World 4" + id + " : " + name;
    }
    // request body - xwww from url encoded - done
    @PostMapping("body/from-url-encoded")
    public String getRequestBodyXWWWFormUrlEncoded(@RequestParam("id")String id,
                                                   @RequestParam("name")String name){
        return "Hello World 5" + id + " : " + name;
    }
    // request body - Json
    @PostMapping("body/json")
    public String getRequestBodyJson(){
        return "Hello World 6";
    }
}
