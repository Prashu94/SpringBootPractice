package com.infosys.services;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.EmployeeDTO;
import com.infosys.exception.EmployeeException;
import com.infosys.repository.EmployeeRepository;
import com.infosys.validator.Validator;

@Service(value="employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Integer addEmployee(EmployeeDTO employeeDTO) throws EmployeeException {
        if(employeeRepository.getEmployeeDetails(employeeDTO.getEmployeeId())!=null){
            throw new EmployeeException("Service.EMPLOYEE_ALREADY_PRESENT");
        }
        Validator.validate(employeeDTO);
        Integer employeeAdded = employeeRepository.addEmployee(employeeDTO);
        return employeeAdded;
    }

    @Override
    public EmployeeDTO getEmployeeDetails(Integer employeeId) throws EmployeeException {
        EmployeeDTO employeeDTO = employeeRepository.getEmployeeDetails(employeeId);
        if(employeeDTO==null){
            throw new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
        }
        return employeeDTO;
    }

    @Override
    public void updateEmployeeDetails(Integer employeeId, String emailId) throws EmployeeException {
        EmployeeDTO employeeDTO = employeeRepository.getEmployeeDetails(employeeId);
        if(employeeDTO==null){
            throw new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
        }
        employeeRepository.updateEmployee(employeeId, emailId);
    }

    @Override
    public void deleteEmployeeDetails(Integer employeeId) throws EmployeeException {
        EmployeeDTO employeeDTO = employeeRepository.getEmployeeDetails(employeeId);
        if(employeeDTO==null){
            throw new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
        }
        employeeRepository.deleteEmployee(employeeId);
    }
    
}
