package com.infosys.entity;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger productCode;
	
	private String productName;
	
	private String productVendor;
	
	private Double productPrice;
	
	@Column(name = "product_in_stock")
	private Integer productInStock;
	
	public Products() {
		super();
	}

	public Products(BigInteger productCode, String productName, String productVendor, Double productPrice,
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
	public int hashCode() {
		return Objects.hash(productCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Objects.equals(productCode, other.productCode);
	}
	
	
}
