package com.infytel.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infytel.dto.PlanDTO;
import com.infytel.repository.PlanRepository;
@Service
public class PlanService 
{
	@Autowired
	private PlanRepository planRepository;
	//contacts repository to fetch plans
	public List<PlanDTO> fetchPlans() 
	{
		return planRepository.fetchPlans();
	}
	//contacts repository to fetch plans by localRates
	public List<PlanDTO> plansLocalRate(List localRates)
	{
		return planRepository.plansLocalRate(localRates);
	}
	
}
