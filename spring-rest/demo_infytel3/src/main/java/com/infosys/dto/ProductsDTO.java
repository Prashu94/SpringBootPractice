package com.infosys.dto;

import java.math.BigInteger;

public class ProductsDTO {
	
	private BigInteger productCode;
	
	private String productName;
	
	private String productVendor;
	
	private Double productPrice;
	
	private Integer productInStock;

	public ProductsDTO() {
		super();
	}

	public ProductsDTO(BigInteger productCode, String productName, String productVendor, Double productPrice,
			Integer productInStock) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productVendor = productVendor;
		this.productPrice = productPrice;
		this.productInStock = productInStock;
	}

	public BigInteger getProductCode() {
		return productCode;
	}

	public void setProductCode(BigInteger productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductInStock() {
		return productInStock;
	}

	public void setProductInStock(Integer productInStock) {
		this.productInStock = productInStock;
	}

	@Override
	public String toString() {
		return "ProductsDTO [productCode=" + productCode + ", productName=" + productName + ", productVendor="
				+ productVendor + ", productPrice=" + productPrice + ", productInStock=" + productInStock + "]";
	}
	
	
}
