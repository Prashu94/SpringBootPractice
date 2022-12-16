package com.infosys.dto;

import java.time.LocalDate;

public class CardDTO {
	
	private Integer cardId;
	private String cardNumber;
	private LocalDate expiryDate;
	
	public CardDTO() {
		super();
	}

	public CardDTO(Integer cardId, String cardNumber, LocalDate expiryDate) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
