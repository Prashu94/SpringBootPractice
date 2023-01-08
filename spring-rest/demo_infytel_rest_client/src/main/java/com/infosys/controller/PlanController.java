package com.infosys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {
	
	private PlanService planService;
	
	
}
