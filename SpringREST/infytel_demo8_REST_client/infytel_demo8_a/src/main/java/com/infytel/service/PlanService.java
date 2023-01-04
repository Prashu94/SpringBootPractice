package com.infytel.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
}
