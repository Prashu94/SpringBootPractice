package com.infosys.services;

import com.infosys.dto.EmployeeDTO;
import com.infosys.exception.EmployeeException;

public interface EmployeeService {
    public Integer addEmployee(EmployeeDTO employeeDTO) throws EmployeeException;

    public EmployeeDTO getEmployeeDetails(Integer employeeId) throws EmployeeException;

    public void updateEmployeeDetails(Integer employeeId, String emailId) throws EmployeeException;

    public void deleteEmployeeDetails(Integer employeeId) throws EmployeeException;
}
