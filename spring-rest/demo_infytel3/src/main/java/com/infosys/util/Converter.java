package com.infosys.util;

import com.infosys.dto.ProductsDTO;
import com.infosys.entity.Products;

public class Converter {
	
	public static Products convertProductDTOToProduct(ProductsDTO productsDTO) {
		Products products = new Products(
				productsDTO.getProductCode(), 
				productsDTO.getProductName(),
				productsDTO.getProductVendor(),
				productsDTO.getProductPrice(),
				productsDTO.getProductInStock()
				);
		
		return products;
	}
	
	public static ProductsDTO convertProductToProductsDTO(Products products) {
		ProductsDTO productsDTO = new ProductsDTO(
				products.getProductCode(),
				products.getProductName(),
				products.getProductVendor(),
				products.getProductPrice(),
				products.getProductInStock()
				);
		return productsDTO;
	}
}
