package com.infytel.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infytel.dto.PlanDTO;
import com.infytel.repository.PlanRepository;
@Service
public class PlanService 
{
	@Autowired
	private PlanRepository planRepository;
	//methods fetchPlans() and plansLocalRate() go here
	
	public PlanDTO fetchPlanById(int planId)
	{
		return planRepository.fetchPlanById(planId);
	} 
	public Map<String, Integer> fetchPlanByIdv2(@PathVariable("planId") int planId)
	{
		return planRepository.fetchPlanByIdv2(planId);
	}
}
