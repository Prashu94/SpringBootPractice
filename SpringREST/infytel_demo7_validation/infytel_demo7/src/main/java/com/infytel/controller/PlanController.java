package com.infytel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infytel.dto.EntityList;
import com.infytel.dto.PlanDTO;
import com.infytel.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController 
{
	private EntityList<PlanDTO> plans;
	@Autowired
	private PlanService planService;   
	
	//Get all available plans
	@GetMapping(produces = {"application/json","application/xml"})
	public EntityList<PlanDTO> fetchPlans() 
	{
		plans = new EntityList<>(planService.fetchPlans());
		return plans;
	}
	
	//Gets plans based on localRate
	@GetMapping(value = "/{query}/plan", produces = {"application/json","application/xml"})
	public EntityList<PlanDTO> plansLocalRate(@MatrixVariable(pathVar="query") Map<String, List<Integer>> map ) 
	{
		 Set<String> keysLocalRates = map.keySet();
		
		 ArrayList localRates = new ArrayList();
		for(String key : keysLocalRates)
		{
			for(int i=0;i<map.get(key).size();i++)
			{
				localRates.add(map.get(key).get(i));
				
			}
		}
		plans = new EntityList<>(planService.plansLocalRate(localRates));
		return plans;
	}

}
