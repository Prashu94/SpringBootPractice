package com.prospring5.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.prospring5.entity.Customer;

@Repository("customerRepository")
public class CustomerDAOImpl implements CustomerDAO{
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	@Override
	public void insert(Customer customer) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}

	@Override
	public int remove(Long phoneNo) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		int result = 0;
		try {
			Customer emp = entityManager.find(Customer.class, phoneNo);
			entityManager.remove(emp);
			result = 1;
			entityManager.getTransaction().commit();
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		return result;
	}

	@Override
	public List<Customer> getAll() {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select c from customer c");
		return (List<Customer>)query.getResultList();
	}

	@Override
	public void update(Long phoneNo, String address) {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Customer cust = entityManager.find(Customer.class, phoneNo);
		cust.setAddress(address);
		entityManager.getTransaction().commit();
	}

}
