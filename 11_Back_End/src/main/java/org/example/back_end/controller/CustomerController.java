package org.example.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.service.custom.CustomerService;
import org.example.back_end.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> saveCustomer(@RequestBody CustomerDTO dto) {
        customerService.saveCustomer(dto);
        return new ResponseEntity<>(new APIResponse<>(201, "Customer Saved Successfully", null), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<APIResponse<String>> updateCustomer(@RequestBody CustomerDTO dto) {
        customerService.updateCustomer(dto);
        return new ResponseEntity<>(new APIResponse<>(200, "Customer Updated Successfully", null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getCustomerData();
        return new ResponseEntity<>(new APIResponse<>(200, "Success", allCustomers), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(new APIResponse<>(200, "Customer Deleted Successfully", null), HttpStatus.OK);
    }
}