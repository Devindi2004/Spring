package org.example.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.service.custom.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController{

    public final CustomerService customerService;

    @PostMapping
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){
     customerService.saveCustomer(customerDTO);
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerService.getCustomerData();
    }
}
