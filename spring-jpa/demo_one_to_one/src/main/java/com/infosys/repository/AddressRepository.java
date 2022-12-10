package com.infosys.repository;

import org.springframework.data.repository.CrudRepository;

import com.infosys.entity.Address;

public interface AddressRepository  extends CrudRepository<Address, Integer>{

}
