package com.infosys.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infosys.dto.EmployeeDTO;
import com.infosys.entity.Employee;

@Repository(value = "employeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setEmailId(employeeDTO.getEmailId());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        entityManager.persist(employee);
        return employee.getEmployeeId();
    }

    @Override
    public EmployeeDTO getEmployeeDetails(Integer employeeId) {
        
        return null;
    }

    @Override
    public void updateEmployee(Integer employeeId, String emailId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        // TODO Auto-generated method stub
        
    }
    
}
