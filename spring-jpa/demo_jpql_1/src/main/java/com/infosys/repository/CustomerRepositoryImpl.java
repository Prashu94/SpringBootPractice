package com.infosys.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerDTO> getCustomerDetails() {
        List<CustomerDTO> customerDTOs = null;
        String queryString = "select c from Customer c";
        Query query = entityManager.createQuery(queryString);
        List<Customer> customers = query.getResultList();
        customerDTOs = new ArrayList<>();
        for(Customer customerEntity: customers){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCity(customerEntity.getCity());
            customerDTO.setCustomerId(customerEntity.getCustomerId());
            customerDTO.setDateOfBirth(customerEntity.getDateOfBirth());
            customerDTO.setEmailId(customerEntity.getEmailId());
            customerDTO.setName(customerEntity.getName());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    @Override
    public List<Object[]> getCustomerNameAndDob() {
        String queryString = "select c.name, c.dateOfBirth from Customer c";
        Query query = entityManager.createQuery(queryString);
        List<Object[]> result = query.getResultList();

        return result;
    }

    @Override
    public List<String> getCustomerName() {
        List<String> customerNames = null;
        String queryString = "select c.name from Customer c";
        Query query = entityManager.createQuery(queryString);
        customerNames = query.getResultList();
        return customerNames;
    }
    
}
