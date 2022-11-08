package com.infy.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Double getAverageBalance() {
		String queryString ="SELECT AVG(a.balance) FROM Account a";
		Query query=entityManager.createQuery(queryString);
		return (Double) query.getSingleResult();	
	}

	@Override
	public Long getTotalBalance() {
		String queryString ="SELECT SUM(a.balance) FROM Account a";
		Query query=entityManager.createQuery(queryString);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long getNumberOfAccounts() {
		String queryString ="SELECT COUNT(a) FROM Account a";
		Query query=entityManager.createQuery(queryString);
		return (Long) query.getSingleResult();
	}

	@Override
	public Integer getMinimumBalance() {
		String queryString ="SELECT MIN(a.balance) FROM Account a";
		Query query=entityManager.createQuery(queryString);
		return (Integer) query.getSingleResult();
	}

	@Override
	public Integer getMaximumBalance() {
		String queryString ="SELECT MAX(a.balance) FROM Account a";
		Query query=entityManager.createQuery(queryString);
		return (Integer)query.getSingleResult();
	}	
}
