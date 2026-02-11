package org.example.back_end.service.impl;

import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.repository.CustomerRepository;
import org.example.back_end.service.custom.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(CustomerDTO customerDTO) {
        customerRepository.save(
                new Customer(
                        customerDTO.getCId(),
                        customerDTO.getCName(),
                        customerDTO.getCAddress(),
                        customerDTO.getCAge()
                ));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.save(
                new Customer(
                        customerDTO.getCId(),
                        customerDTO.getCName(),
                        customerDTO.getCAddress(),
                        customerDTO.getCAge()
                ));
    }

    @Override
    public List<Customer> getCustomerData() {
         List<Customer> customerList = customerRepository.findAll();

         return customerList;
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        customerRepository.deleteById(customerDTO.getCId());
    }
}
