package com.infosys.repository;

import org.springframework.data.repository.CrudRepository;

import com.infosys.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
