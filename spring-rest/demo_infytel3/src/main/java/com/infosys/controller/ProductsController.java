package com.infosys.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.EntityList;
import com.infosys.dto.ProductsDTO;
import com.infosys.exceptions.ProductNotFoundException;
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

	@GetMapping(value = "/{productName}")
	public ResponseEntity<List<ProductsDTO>> fetchCustomer(@PathVariable("productName") String productName) {
		return ResponseEntity.ok(productService.fetchProducts(productName));
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ProductsDTO>> getCustomer(@RequestParam("productName") String productName,
			@RequestParam("productVendor") String productVendor) {
		return ResponseEntity.ok(productService.getProducts(productName, productVendor));
	}
	
	// Example: http://localhost:8081/infyproducts-1/product/query;vendor=Britannia,Dairy Milk/vendor/
	@GetMapping(value = "/{query}/vendor", produces = {"application/json", "application/xml"})
	public EntityList<ProductsDTO> getProducts(@MatrixVariable(pathVar = "query") Map<String, List<String>> map){
		Set<String> keyVendor = map.keySet();
		List<String> productVendors = new ArrayList<>();
		for(String key : keyVendor) {
			for(int i = 0; i<map.get(key).size(); i++) {
				productVendors.add(map.get(key).get(i));
			}
		}
		// If we want the data in JSON
		//return new EntityList<>(productService.getProducts(productVendors)).getItems();
		return new EntityList<>(productService.getProducts(productVendors));
	}
	
	@DeleteMapping("/{productCode}")
	public String deleteProduct(@PathVariable("productCode") String productCode) throws ProductNotFoundException {
		return productService.deleteProducts(new BigInteger(productCode));
	} 
	
	
}
