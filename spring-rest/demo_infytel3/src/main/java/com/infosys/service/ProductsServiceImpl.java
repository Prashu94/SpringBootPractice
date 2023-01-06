package com.infosys.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.ProductsDTO;
import com.infosys.entity.Products;
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
	
	
}
