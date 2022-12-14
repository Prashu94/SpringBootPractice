package com.infosys.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl  implements CustomerRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CustomerDTO getCustomerDTO(Integer customerId) {
        CustomerDTO customerDTO = null;
        Customer customer = entityManager.find(Customer.class, customerId);
        if(customer!=null){
            customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setName(customer.getName());
            customerDTO.setCustomerType(customer.getCustomerType());
        }
        return customerDTO;
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer  = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setName(customerDTO.getName());
        customer.setCustomerType(customerDTO.getCustomerType());
        entityManager.persist(customer);
    }

    @Override
    public Integer updateCustomer(Integer customerId, String emailId) {
        Integer customerIdReturned = null;
        Customer customer = entityManager.find(Customer.class, customerId);
        customer.setEmailId(emailId);
        Customer customerUpdated = entityManager.merge(customer);
        customerIdReturned = customerUpdated.getCustomerId();
        return customerIdReturned;
    }

    @Override
    public Integer deleteCustomer(Integer customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        entityManager.remove(customer);
        Integer customerIdToBeReturned = customer.getCustomerId();
        return customerIdToBeReturned;
    }
    
}
