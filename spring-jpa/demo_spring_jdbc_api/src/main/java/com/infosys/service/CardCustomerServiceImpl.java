package com.infosys.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CardDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Card;
import com.infosys.entity.Customer;
import com.infosys.exception.InfyBankException;
import com.infosys.repository.CardRepository;
import com.infosys.repository.CustomerRepository;

@Service(value = "cardCustomerService")
@Transactional
public class CardCustomerServiceImpl implements CardCustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException {
        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("SERVICE.CUSTOMER_NOT_FOUND"));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmailId(customer.getEmailId());
        customerDTO.setName(customer.getName());
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());

        List<Card> cards = customer.getCards();
        List<CardDTO> cardDTOs = new LinkedList<>();

        if(!cards.isEmpty()){
            cardDTOs = cards.stream()
                        .map(c -> new CardDTO(c.getCardId(), c.getCardNumber(), c.getExpiryDate()))
                        .collect(Collectors.toList());
        }
        customerDTO.setCards(cardDTOs);
        return customerDTO;
    }

    @Override
    public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        List<CardDTO> cardDTOs = customerDTO.getCards();
        List<Card> cards;

        cards = cardDTOs.stream()
                .map(c -> new Card(c.getCardId(), c.getCardNumber(), c.getExpiryDate()))
                .collect(Collectors.toList());
        customer.setCards(cards);
        customerRepository.save(customer);
        return customer.getCustomerId();
    }

    @Override
    public void issueCardToExistingCustomer(Integer customerId, CardDTO cardDTO) throws InfyBankException {
        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        Card card = new Card();
        card.setCardId(cardDTO.getCardId());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setExpiryDate(cardDTO.getExpiryDate());

        List<Card> cards = customer.getCards();
        cards.add(card);
        customer.setCards(cards);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete)
            throws InfyBankException {
        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        for(Integer cardId: cardIdsToDelete){
            Optional<Card> optionalCard = cardRepository.findById(cardId);
            if(optionalCard.isPresent()){
                customer.getCards().remove(optionalCard.orElse(null));
                cardRepository.deleteById(cardId);
            }
        }
        
    }

    @Override
    public void deleteCustomer(Integer customerId) throws InfyBankException {
        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRepository.delete(customer);
    }
    
}
