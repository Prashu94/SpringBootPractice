package com.infosys.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.CallDetailsDTO;
import com.infosys.repository.CallDetailsRespository;

@Service
public class CallDetailsService {
	
	@Autowired
	private CallDetailsRespository callDetailsRepository;
	
	// contacts repository to fetch the call details
	public List<CallDetailsDTO> fetchCallDetails(long calledBy, LocalDate calledOn){
		return callDetailsRepository.fetchCallDetails(calledBy, calledOn);
	}
	
	
}
