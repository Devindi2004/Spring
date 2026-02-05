package org.example.back_end.service.custom;

import org.example.back_end.dto.CustomerDTO;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);
}
