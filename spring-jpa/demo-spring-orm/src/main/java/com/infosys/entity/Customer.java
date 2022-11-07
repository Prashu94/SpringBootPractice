package com.infosys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    
    @Id
    @Column(name="phone_no")
    private int phoneNumber;

    private String name;
    private Integer age;
    private Character gender;
    private String address;

    @Column(name = "plan_id")
    private Integer planId;

    public Customer() {
    }

    
}
