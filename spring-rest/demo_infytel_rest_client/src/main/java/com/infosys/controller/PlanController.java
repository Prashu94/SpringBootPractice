package com.infosys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.PlanDTO;
import com.infosys.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("/{planId}")
	public PlanDTO fetchPlanById(@PathVariable("planId") int planId) {
		return planService.fetchPlanId(planId);
	}
	
	
}
