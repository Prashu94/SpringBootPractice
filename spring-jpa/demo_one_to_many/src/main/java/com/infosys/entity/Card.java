package com.infosys.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {
	
	@Id
	private Integer cardId;
	private String cardNumber;
	private LocalDate expiryDate;
	
	
	public Card() {
		super();
	}


	public Card(Integer cardId, String cardNumber, LocalDate expiryDate) {
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


	@Override
	public int hashCode() {
		return Objects.hash(cardId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(cardId, other.cardId);
	}
	
	
	
}
