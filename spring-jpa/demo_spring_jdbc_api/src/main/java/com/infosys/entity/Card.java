package com.infosys.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {
    
    @Id
    private Integer cardId;
    private String cardNumber;
    private LocalDate expiryDate;
    
    public Card() {
    }

    public Card(Integer cardId, String cardNumber, LocalDate expiryDate) {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
        result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        return result;
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
        if (cardId == null) {
            if (other.cardId != null)
                return false;
        } else if (!cardId.equals(other.cardId))
            return false;
        if (cardNumber == null) {
            if (other.cardNumber != null)
                return false;
        } else if (!cardNumber.equals(other.cardNumber))
            return false;
        if (expiryDate == null) {
            if (other.expiryDate != null)
                return false;
        } else if (!expiryDate.equals(other.expiryDate))
            return false;
        return true;
    }

    
    
}
