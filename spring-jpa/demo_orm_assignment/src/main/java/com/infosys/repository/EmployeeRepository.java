package com.infosys.repository;

import com.infosys.dto.EmployeeDTO;

public interface EmployeeRepository {
    public Integer addEmployee(EmployeeDTO employeeDTO);

    public EmployeeDTO getEmployeeDetails(Integer employeeId);

    public void updateEmployee(Integer employeeId, String emailId);

    public void deleteEmployee(Integer employeeId);
}
