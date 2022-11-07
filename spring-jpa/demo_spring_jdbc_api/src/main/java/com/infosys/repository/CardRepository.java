package com.infosys.repository;

import org.springframework.data.repository.CrudRepository;

import com.infosys.entity.Card;

public interface CardRepository extends CrudRepository<Card, Integer>{
    
}
