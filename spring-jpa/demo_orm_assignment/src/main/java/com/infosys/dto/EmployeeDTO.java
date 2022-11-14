package com.infosys.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    
    private Integer employeeId;
    private String employeeName;
    private String emailId;
    private LocalDate dateOfBirth;
    private ManufacturingUnit manufacturingUnit;
    
    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public ManufacturingUnit getManufacturingUnit() {
        return manufacturingUnit;
    }
    public void setManufacturingUnit(ManufacturingUnit manufacturingUnit) {
        this.manufacturingUnit = manufacturingUnit;
    }
    @Override
    public String toString() {
        return "EmployeeDTO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", emailId=" + emailId
                + ", dateOfBirth=" + dateOfBirth + ", manufacturingUnit=" + manufacturingUnit + "]";
    }
    
}
