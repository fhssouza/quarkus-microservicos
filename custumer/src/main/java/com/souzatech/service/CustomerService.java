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

    public List<CustomerDTO> findAllCustumers() {
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll().stream().forEach(item->{
            customers.add(mapCustomerEntityToDTO(item));
        });

        return customers;
    }

    public void createNewCustomer(CustomerDTO customerDTO) {
        customerRepository.persist(mapCustomerDtoToEntity(customerDTO));
    }

    public void changeCustomer(Long id, CustomerDTO customerDTO) {

        CustomerEntity customerEntity = customerRepository.findById(id);

        customerEntity.setName(customerDTO.getName());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setAge(customerDTO.getAge());

        customerRepository.persist(customerEntity);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private static CustomerEntity mapCustomerDtoToEntity(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(customerDTO.getName());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setAge(customerDTO.getAge());

        return customerEntity;
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
