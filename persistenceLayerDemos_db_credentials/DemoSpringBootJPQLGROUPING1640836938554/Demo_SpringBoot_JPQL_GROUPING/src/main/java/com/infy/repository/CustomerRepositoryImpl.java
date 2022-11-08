package com.infy.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object[]> getCustomerCountForCities() {

		String queryString1 = "SELECT c.city, COUNT(c) FROM Customer c GROUP BY c.city";

		String queryString2 = "SELECT c.city, COUNT(c) FROM Customer c GROUP BY c.city HAVING c.city IN ('Seattle','Vancouver')";

		Query query = entityManager.createQuery(queryString2);

		List<Object[]> result = query.getResultList();

		return result;
	}

}
