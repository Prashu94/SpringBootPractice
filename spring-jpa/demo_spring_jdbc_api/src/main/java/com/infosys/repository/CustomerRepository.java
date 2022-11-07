package com.infosys.repository;
import org.springframework.data.repository.CrudRepository;

import com.infosys.entity.Customer;
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    
}
