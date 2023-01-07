package com.infosys.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infosys.entity.Products;


public interface ProductRepository extends JpaRepository<Products, BigInteger> {
	
	public List<Products> findByProductName(String name);
	
	public List<Products> findByProductNameAndProductVendor(String productName, String productVendor);
	
	@Query("SELECT p FROM Products p where p.productVendor IN :productVendors")
	public List<Products> findByProductVendorIn(@Param("productVendors") List<String> productVendors);
}
