package com.infosys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.PlanDTO;
import com.infosys.repository.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	public PlanDTO fetchPlanId(int planId) {
		return planRepository.fetchPlanById(planId);
	}
}
