package org.example.back_end.service.custom;

import org.example.back_end.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomerData();

    void deleteCustomer(int customerDTO);
}
