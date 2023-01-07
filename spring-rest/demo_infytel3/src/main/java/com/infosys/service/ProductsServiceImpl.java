package com.infosys.service;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.ProductsDTO;
import com.infosys.entity.Products;
import com.infosys.exceptions.ProductNotFoundException;
import com.infosys.repository.ProductRepository;
import com.infosys.util.Converter;

@Service(value = "productsService")
@Transactional
public class ProductsServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public String addProduct(ProductsDTO productsDTO) {
		Products products = Converter.convertProductDTOToProduct(productsDTO);
		
		productRepository.save(products);
		
		return products.getProductName();
	}

	@Override
	public List<ProductsDTO> fetchProducts(String productName) {
		List<Products> products = productRepository.findByProductName(productName);
		
		List<ProductsDTO> productsDTO = new ArrayList<ProductsDTO>();
		
		for(Products product: products) {
			productsDTO.add(Converter.convertProductToProductsDTO(product));
		}
		
		return productsDTO;
	}

	@Override
	public List<ProductsDTO> getProducts(String productName, String productVendor) {
		List<Products> products = productRepository.findByProductNameAndProductVendor(productName, productVendor);
		
		List<ProductsDTO> productsDTO = new ArrayList<ProductsDTO>();
		
		for(Products product: products) {
			productsDTO.add(Converter.convertProductToProductsDTO(product));
		}
		return productsDTO;
	}

	@Override
	public List<ProductsDTO> getProducts(List<String> productVendors) {
		List<Products> products = productRepository.findByProductVendorIn(productVendors);
		
		List<ProductsDTO> productsDTO = new ArrayList<ProductsDTO>();
		
		for(Products product: products) {
			productsDTO.add(Converter.convertProductToProductsDTO(product));
		}
		return productsDTO;
	}

	@Override
	public String deleteProducts(BigInteger productCode) throws ProductNotFoundException {
		String response = "";
		Optional<Products> optional = productRepository.findById(productCode);
		Products product = optional.orElseThrow(() -> new ProductNotFoundException("Product With Product Code: "+ productCode + " not found"));
		if(product!=null) {
			productRepository.delete(product);
			response = "Product With Product Code: "+ productCode + " found";
		}
		return response;
	}
	
	
}
