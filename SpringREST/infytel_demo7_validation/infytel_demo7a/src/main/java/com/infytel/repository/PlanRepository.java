package com.infytel.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.infytel.dto.PlanDTO;

@Repository
public class PlanRepository 
{
private List<PlanDTO> plans;
	
	//Populating a list of plans
	@PostConstruct
	public void populatePlans()
	{
		plans = new ArrayList<>();
		PlanDTO plan1= new PlanDTO();
		plan1.setPlanId(1);
		plan1.setPlanName("Simple");
		plan1.setLocalRate(3);
		plan1.setNationalRate(5);
		plans.add(plan1);
		PlanDTO plan2= new PlanDTO();
		plan2.setPlanId(2);
		plan2.setPlanName("Medium");
		plan2.setLocalRate(5);
		plan2.setNationalRate(8);
		plans.add(plan2);
		
	}
	
	//fetching all available plans
	public List<PlanDTO> fetchPlans() 
	{
		return plans;
	}
	
	//fetching plans based on localRate
	public List<PlanDTO> plansLocalRate(List localRates)
	{
		List<PlanDTO> plansResponse = new ArrayList<>();
		
		Iterator it = localRates.iterator();
		while(it.hasNext())
		{
			int rate =Integer.parseInt((String)it.next());
			for(PlanDTO plan  : plans)
			{
				if(rate == plan.getLocalRate())
					plansResponse.add(plan);
			}
		}
		return plansResponse;
	}
	
}
