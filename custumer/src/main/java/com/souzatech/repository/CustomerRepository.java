package com.souzatech.repository;

import com.souzatech.entity.CustomerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerEntity> {
}
