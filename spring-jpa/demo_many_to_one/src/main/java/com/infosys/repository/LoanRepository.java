package com.infosys.repository;

import org.springframework.data.repository.CrudRepository;

import com.infosys.entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, Integer>{

}
