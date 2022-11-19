package com.infosys.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.entity.Account;

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

    public List<CustomerDTO> getCustomerDetails(Integer customerId){
        List<CustomerDTO> customerDTOs = null;
        String queryString = "select c from Customer c where c.customerId = ?1";
        Query query = entityManager.createQuery(queryString);
        query.setParameter(1, customerId);
        
        List<Customer> customers = query.getResultList();
        customerDTOs = new ArrayList<>();
        for(Customer customer: customers) {
        	CustomerDTO customerDTO = new CustomerDTO();
        	customerDTO.setCity(customer.getCity());
        	customerDTO.setCustomerId(customer.getCustomerId());
        	customerDTO.setDateOfBirth(customer.getDateOfBirth());
        	customerDTO.setEmailId(customer.getEmailId());
        	customerDTO.setName(customer.getName());
        	customerDTOs.add(customerDTO);
        }
    	return customerDTOs;
    }
    
    public List<CustomerDTO> getCustomerDetailsV1(Integer customerId){
    	List<CustomerDTO> customerDTOs = null;
    	String queryString = "select c from Customer c where c.customerId = :customerId";
    	Query query = entityManager.createQuery(queryString);
    	query.setParameter("customerId", customerId);
    	
    	List<Customer> customers = query.getResultList();
    	customerDTOs = new ArrayList<>();
    	for(Customer customer: customers) {
    		CustomerDTO customerDTO = new CustomerDTO();
    		customerDTO.setCity(customer.getCity());
    		customerDTO.setCustomerId(customer.getCustomerId());
    		customerDTO.setDateOfBirth(customer.getDateOfBirth());
    		customerDTO.setEmailId(customer.getEmailId());
    		customerDTO.setName(customer.getName());
    		customerDTOs.add(customerDTO);
    	}
    	return customerDTOs;
    }

	@Override
	public List<CustomerDTO> getCustomerDetailsWhereClauses() {
		List<CustomerDTO> customerDTOs = null;
		String queryString1 = "SELECT c from Customer c where c.customerId = 1001";
		String queryString2 = "SELECT c from Customer c where c.city != 'Seattle'";
		String queryString3 = "SELECT c from Customer c where c.dateOfBirth > '1990-01-01'";
		String queryString4 = "SELECT c from Customer c where c.dateOfBirth >= '1990-01-01'";
		String queryString5 = "SELECT c from Customer c where c.dateOfBirth < '1992-01-01'";
		String queryString6 = "SELECT c from Customer c where c.dateOfBirth <= '1992-01-01'";
		String queryString7 = "SELECT c from Customer c where c.dateOfBirth BETWEEN '1980-01-01' and '1995-01-01'";
		String queryString8 = "SELECT c from Customer c where c.name LIKE 'J%'";
		String queryString9 = "SELECT c from Customer c where c.emailId IS NULL";
		String queryString10 = "SELECT c from Customer c where c.city IN ('Seattle', 'Vancouver')";
		
		Query query = entityManager.createQuery(queryString10);
		List<Customer> customers = query.getResultList();
    	customerDTOs = new ArrayList<>();
    	for(Customer customer: customers) {
    		CustomerDTO customerDTO = new CustomerDTO();
    		customerDTO.setCity(customer.getCity());
    		customerDTO.setCustomerId(customer.getCustomerId());
    		customerDTO.setDateOfBirth(customer.getDateOfBirth());
    		customerDTO.setEmailId(customer.getEmailId());
    		customerDTO.setName(customer.getName());
    		customerDTOs.add(customerDTO);
    	}
    	return customerDTOs;
	}

	@Override
	public Double getAverageBalance() {
		String queryString = "SELECT AVG(a.balance) from Account a";
		Query query = entityManager.createQuery(queryString);
		return (Double)query.getSingleResult();
	}

	@Override
	public Long getTotalBalance() {
		String queryString = "SELECT SUM(a.balance) from Account a";
		Query query = entityManager.createQuery(queryString);
		return (Long)query.getSingleResult();
	}

	@Override
	public Long getNumberOfAccounts() {
		String queryString = "SELECT COUNT(a.balance) from Account a";
		Query query = entityManager.createQuery(queryString);
		return (Long)query.getSingleResult();
	}

	@Override
	public Integer getMinimumBalance() {
		String queryString = "SELECT MIN(a.balance) from Account a";
		Query query = entityManager.createQuery(queryString);
		return (Integer)query.getSingleResult();
	}

	@Override
	public Integer getMaximumBalance() {
		String queryString = "SELECT MAX(a.balance) from Account a";
		Query query = entityManager.createQuery(queryString);
		return (Integer)query.getSingleResult();
	}

	@Override
	public List<Object[]> getCustomerCountForCities() {
		String queryString1 = "SELECT c.city, COUNT(c) from Customer c GROUP BY c.city";
		String queryString2 = "SELECT c.city, COUNT(c) from Customer c GROUP BY c.city HAVING c.city IN ('Seattle', 'Vancouver')";
		Query query = entityManager.createQuery(queryString2);
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public Integer updateCityOfCustomer(Integer customerId, String city) {
		Query query = entityManager.createQuery("UPDATE Customer c SET c.city = ?1 where c.customerId = ?2");
		query.setParameter(1, city);
		query.setParameter(2, customerId);
		return query.executeUpdate();
	}

	@Override
	public Integer deleteCustomer() {
		Query query = entityManager.createQuery("DELETE FROM Customer c where c.emailId is NULL");
		return query.executeUpdate();
	}
    
}
