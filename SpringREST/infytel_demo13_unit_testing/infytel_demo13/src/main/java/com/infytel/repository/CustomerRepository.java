package com.infytel.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.infytel.dto.CustomerDTO;
import com.infytel.dto.FriendFamilyDTO;
import com.infytel.dto.PlanDTO;

@Repository
public class CustomerRepository 
{
	List<CustomerDTO> customers = null;
	//populates customer in hard-coded way
	@PostConstruct
	public void initializer()
	{
		CustomerDTO customerDTO = new CustomerDTO();
		PlanDTO planDTO = new PlanDTO();
		planDTO.setPlanId(1);
		planDTO.setPlanName("Simple");
		planDTO.setLocalRate(3);
		planDTO.setNationalRate(5);
		customerDTO.setAddress("Chennai");
		customerDTO.setAge(18); 
		customerDTO.setCurrentPlan(planDTO);
				customerDTO.setGender('m');
		customerDTO.setName("Jack"); 
		customerDTO.setEmail("Jack@infy.com");
		customerDTO.setPassword("ABC@123"); 
		customerDTO.setPhoneNo(9951212222l);
		List<FriendFamilyDTO> friendAndFamily = new ArrayList<>();
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(),800000145));
		friendAndFamily.add(new FriendFamilyDTO(customerDTO.getPhoneNo(),700000145));
		customerDTO.setFriendAndFamily(friendAndFamily);
		customers = new ArrayList<>();
		customers.add(customerDTO);
	}
	//creates customer
	public String createCustomer(CustomerDTO customerDTO)
	{
		customers.add(customerDTO);
		return "Customer with"+customerDTO.getPhoneNo()+"added successfully";
	}
	//fetches customer
	public List<CustomerDTO> fetchCustomer()
	{
		return customers;
	}
	//deletes customer
	public String deleteCustomer(long phoneNumber)
	{
		String response = "Customer of:"+phoneNumber+"\t does not exist";
		for(CustomerDTO customer : customers)
		{ 
			if(customer.getPhoneNo() == phoneNumber)
			{
				customers.remove(customer);
				response = customer.getName()+"of phoneNumber"+customer.getPhoneNo()+"\t got deleted successfully";
				break;
			}
		}
		return response;
	}
	//finds the customer based on phoneNumber and updates the details of the same
		public String updateCustomer(long phoneNumber, CustomerDTO customerDTO)
		{
			String response = "Customer of: "+phoneNumber+" does not exist";
			StringBuilder modifiedInfo = new StringBuilder("");
			for(CustomerDTO customer : customers)
			{
				if(customer.getPhoneNo() == phoneNumber)
				{
					if(customerDTO.getAddress()!=null)
					{
						customer.setAddress(customerDTO.getAddress());
						modifiedInfo.append(customer.getAddress()).append(" and ");
					}
					if(customerDTO.getEmail()!=null)
					{
						customer.setEmail(customerDTO.getEmail());
						modifiedInfo.append(customer.getEmail());
					}
					response = "Customer of phoneNumber "+customer.getPhoneNo()+"\'s modified details: "+modifiedInfo;
					break;
				}
			}
			return response;
		}

}
