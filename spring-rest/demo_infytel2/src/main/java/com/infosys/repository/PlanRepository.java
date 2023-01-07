package com.infosys.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.infosys.dto.PlanDTO;

@Repository
public class PlanRepository {
	
	private List<PlanDTO> plans;
	
	@PostConstruct
	public void populatePlans() {
		plans = new ArrayList<PlanDTO>();
		
		PlanDTO plan1 = new PlanDTO();
		plan1.setPlanId(1);
		plan1.setPlanName("Simple");
		plan1.setLocalRate(3);
		plan1.setNationalRate(5);
		plans.add(plan1);
		
		
		PlanDTO plan2 = new PlanDTO();
		plan2.setPlanId(2);
		plan2.setPlanName("Medium");
		plan2.setLocalRate(5);
		plan2.setNationalRate(8);
		plans.add(plan2);
		
	}
	
	// method to fetch the plans
	public List<PlanDTO> fetchPlans(){
		return plans;
	}
	
	// method to fetch Plans based on LocalRate
	public List<PlanDTO> plansLocalRate(List localRates){
		List<PlanDTO> plansResponse = new ArrayList<PlanDTO>();
		Iterator it = localRates.iterator();
		while(it.hasNext()) {
			int rate = Integer.parseInt((String)it.next());
			for(PlanDTO plan: plans) {
				if(rate == plan.getLocalRate()) {
					plansResponse.add(plan);
				}
			}
		}
		return plansResponse;
	}
}
