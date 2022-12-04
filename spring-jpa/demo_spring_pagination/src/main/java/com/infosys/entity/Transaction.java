package com.infosys.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id
	private Integer transactionId;
	private LocalDate transactionDate;
	private Float transactionAmount;
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Float getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(transactionId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(transactionId, other.transactionId);
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", transactionAmount=" + transactionAmount + "]";
	}
	
	
}
