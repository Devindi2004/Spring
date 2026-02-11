package org.example.back_end.service.custom;

import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.entity.Customer;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);

    List<Customer> getCustomerData();

    void deleteCustomer(CustomerDTO customerDTO);
}
