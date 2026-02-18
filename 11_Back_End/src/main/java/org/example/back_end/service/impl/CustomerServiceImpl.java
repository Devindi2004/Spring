package org.example.back_end.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.CustomerDTO;
import org.example.back_end.entity.Customer;
import org.example.back_end.repository.CustomerRepository;
import org.example.back_end.service.custom.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        // Relationships nisa manual mapping ho ModelMapper settings check karanna
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        // Relationships null widiyata save wenna hadanna (Entity eke cascade nisa)
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (!customerRepository.existsById(customerDTO.getCId())) {
            throw new RuntimeException("Customer not found with ID: " + customerDTO.getCId());
        }
        customerRepository.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public List<CustomerDTO> getCustomerData() {
        List<Customer> allCustomers = customerRepository.findAll();
        return modelMapper.map(allCustomers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    @Override
    public void deleteCustomer(int id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}