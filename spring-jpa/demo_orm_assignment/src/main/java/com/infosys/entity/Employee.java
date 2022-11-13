package com.infosys.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Employee {
    
    private Integer employeeId;
    private String employeeName;
    private String emailId;
    private LocalDate dateOfBirth;
    
}
