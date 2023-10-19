package com.souzatech.service;

import com.souzatech.dto.CustomerDTO;
import com.souzatech.entity.CustomerEntity;
import com.souzatech.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll().stream().forEach(item->{
            customers.add(mapCustomerEntityToDTO(item));
        });

        return customers;
    }

    private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customer){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setAddress(customer.getAddress());
        customerDTO.setAge(customer.getAge());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;
    }
}
