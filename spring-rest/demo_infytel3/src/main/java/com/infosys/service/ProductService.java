package com.infosys.service;


import java.math.BigInteger;
import java.util.List;

import com.infosys.dto.ProductsDTO;
import com.infosys.exceptions.ProductNotFoundException;

public interface ProductService {
	
	public String addProduct(ProductsDTO productsDTO);
	
	public List<ProductsDTO> fetchProducts(String productName);
	
	public List<ProductsDTO> getProducts(String productName, String productVendor);
	
	public List<ProductsDTO> getProducts(List<String> productVendors);
	
	public String deleteProducts(BigInteger productCode) throws ProductNotFoundException;
}
