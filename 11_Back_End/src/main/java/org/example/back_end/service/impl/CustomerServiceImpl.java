package org.example.back_end.service.impl;

import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.exeception.CustomException;
import org.example.back_end.repository.CustomerRepository;
import org.example.back_end.service.custom.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(CustomerDTO customerDTO) {
        customerRepository.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new CustomException("Customer ID is null");
        }
        customerRepository.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public List<CustomerDTO> getCustomerData() {
        List<Customer> list = customerRepository.findAll();

        return modelMapper.map(list, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        customerRepository.deleteById(Integer.parseInt(String.valueOf(customerDTO.getCId())));
    }
}