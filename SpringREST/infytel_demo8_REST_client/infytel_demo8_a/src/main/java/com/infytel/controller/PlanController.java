package com.infytel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infytel.dto.PlanDTO;
import com.infytel.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController 
{
	
	@Autowired
	private PlanService planService;   
	
	//method for getting all the available plans will go here
	//and
	//method for getting the plans besed on local rate will go here
	
	//fetching the plans by id
	@GetMapping("/{planId}")
	public PlanDTO fetchPlanById(@PathVariable("planId") int planId)
	{
		return planService.fetchPlanById(planId);
	}
}
