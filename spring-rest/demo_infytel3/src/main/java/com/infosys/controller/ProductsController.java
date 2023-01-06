package com.infosys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.ProductsDTO;
import com.infosys.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> addCustomer(@RequestBody ProductsDTO productsDTO) {
		String productName = productService.addProduct(productsDTO);
		return ResponseEntity.ok(productName);
	}
}
