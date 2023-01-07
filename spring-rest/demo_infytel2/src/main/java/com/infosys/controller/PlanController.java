package com.infosys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.EntityList;
import com.infosys.dto.PlanDTO;
import com.infosys.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {
	
	private EntityList<PlanDTO> plans;
	
	@Autowired
	private PlanService planService;
	
	// Get all the avaialble plans
	@GetMapping(produces = {"application/xml"})
	public EntityList<PlanDTO> fetchPlans(){
		plans = new EntityList<>(planService.fetchPlans());
		return plans;
	}
	
	@GetMapping(value = "/{query}/plan", produces = {"application/json", "application/xml"})
	public EntityList<PlanDTO> plansLocalRate(@MatrixVariable(pathVar = "query") Map<String, List<Integer>> map){
		Set<String> keyLocalRates = map.keySet();
		List localRates = new ArrayList();
		for(String key: keyLocalRates) {
			for(int i = 0; i<map.get(key).size(); i++) {
				localRates.add(map.get(key).get(i));
			}
		}
		plans = new EntityList<>(planService.plansLocalRates(localRates));
		return plans;
	}
	
}
