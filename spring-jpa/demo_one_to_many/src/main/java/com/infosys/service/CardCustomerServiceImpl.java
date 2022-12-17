package com.infosys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.dto.CardDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Card;
import com.infosys.entity.Customer;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.CardRepository;
import com.infosys.repository.CustomerRepository;
import com.infosys.utility.Converter;

@Service(value = "cardCustomerService")
@Transactional
public class CardCustomerServiceImpl implements CardCustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Override
	public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDTO = Converter.convertCustomerEntityToDTO(customer);
		return customerDTO;
	}

	@Override
	public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException {
		Customer customer = Converter.convertCustomerDTOToEntity(customerDTO);
		
		customerRepository.save(customer);
		return customer.getCustomerId();
	}

	@Override
	public void issueCardToExistingCustomer(Integer customerId, CardDTO cardDTO) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(()->new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		Card card = Converter.convertCardDTOToEntity(cardDTO);
		List<Card> c = customer.getCards();
		c.add(card);
		customer.setCards(c);
		customerRepository.save(customer);
	}

	@Override
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardsToDelete) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		for(Integer cardId: cardsToDelete) {
			Optional<Card> optionalCard = cardRepository.findById(cardId);
			if(optionalCard.isPresent()) {
				customer.getCards().remove(optionalCard.orElse(null));
				cardRepository.deleteById(cardId);
			}
		}
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(()->new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.delete(customer);
	}

}
