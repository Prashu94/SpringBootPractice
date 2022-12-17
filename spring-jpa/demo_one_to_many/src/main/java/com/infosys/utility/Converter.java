package com.infosys.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.infosys.dto.CardDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Card;
import com.infosys.entity.Customer;

public class Converter {
	
	public static CustomerDTO convertCustomerEntityToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setEmailId(customer.getEmailId());
		customerDTO.setName(customer.getName());
		
		List<Card> cards = customer.getCards();
		List<CardDTO> cardDTOs = new ArrayList<CardDTO>();
		
		if(!cards.isEmpty()) {
			cardDTOs = cards.stream()
					.map(c->new CardDTO(c.getCardId(),c.getCardNumber(),c.getExpiryDate()))
					.collect(Collectors.toList());
		}
		
		customerDTO.setCards(cardDTOs);
		return customerDTO;
	}
	
	public static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setDateOfBirth(customer.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		
		List<CardDTO> cardDTOs = customerDTO.getCards();
		List<Card> cards;
		
		cards = cardDTOs
				.stream()
				.map(c -> new Card(c.getCardId(), c.getCardNumber(), c.getExpiryDate()))
				.collect(Collectors.toList());
		
		customer.setCards(cards);
		return customer;
	}
	
	
	public static Card convertCardDTOToEntity(CardDTO cardDTO) {
		Card card = new Card();
		card.setCardId(cardDTO.getCardId());
		card.setCardNumber(cardDTO.getCardNumber());
		card.setExpiryDate(cardDTO.getExpiryDate());
		return card;
	}
}
