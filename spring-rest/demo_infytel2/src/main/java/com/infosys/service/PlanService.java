package com.infosys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.PlanDTO;
import com.infosys.repository.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	// contacts repository to fetch plans
	public List<PlanDTO> fetchPlans(){
		return planRepository.fetchPlans();
	}
	
	// contacts repository to fetch Plan by localRates
	public List<PlanDTO> plansLocalRates(List localRates){
		return planRepository.plansLocalRate(localRates);
	}
}
