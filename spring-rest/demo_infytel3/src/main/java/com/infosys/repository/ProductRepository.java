package com.infosys.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.entity.Products;


public interface ProductRepository extends JpaRepository<Products, BigInteger> {

}
