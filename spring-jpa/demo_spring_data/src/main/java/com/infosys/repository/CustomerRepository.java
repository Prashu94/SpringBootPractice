package com.infosys.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	// Query creation based on method names
	Optional<Customer> findByEmailId(String emailId);
	Optional<Customer> findByEmailIdAndName(String emailId, String name);
	List<Customer> findByEmailIdOrName(String emailId, String name);
	List<Customer> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate);
	List<Customer> findByDateOfBirthLessThan(LocalDate dateOfBirth);
	List<Customer> findByDateOfBirthGreaterThan(LocalDate dateOfBirth);
	List<Customer> findByDateOfBirthAfter(LocalDate dateOfBirth);
	List<Customer> findByDateOfBirthBefore(LocalDate dateOfBirth);
	List<Customer> findByEmailIdNull();
	List<Customer> findByNameLike(String pattern);
	List<Customer> findByNameOrderByDateOfBirth(String name);
	List<Customer> findByNameOrderByDateOfBirthDesc(String name);
	
	// Query creation using @Query
	@Query("SELECT c.name FROM Customer c WHERE c.emailId = :emailId")
	String findNameByEmailId(@Param("emailId") String emailId);
	
	@Query("UPDATE Customer c SET c.emailId = :emailId WHERE c.customerId = :customerId")
	@Modifying
	@Transactional
	Integer updateCustomerEmailId(@Param("emailId") String updateCustomerByEmailId, @Param("customerId") Integer customerId);
	
	@Query("DELETE FROM Customer c WHERE c.emailId = :emailId")
	@Modifying
	@Transactional
	Integer deleteCustomerByEmailId(@Param("emailId") String emailId);
	
	// Query creation using @NamedQuery
	String findNameByEmailIdV1(@Param("emailId") String emailId);
}
