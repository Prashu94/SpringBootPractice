package com.infytel.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infytel.dto.CallDetailsDTO;
import com.infytel.repository.CallDetailsRepository;
@Service
public class CallDetailsService 
{
	@Autowired
	private CallDetailsRepository callDetailsRepository;
	//contacts repository to fetch the call details
	public List<CallDetailsDTO> fetchCallDetails(long calledBy, LocalDate calledOn)
	{
		return callDetailsRepository.fetchCallDetails(calledBy, calledOn);
	}

}
