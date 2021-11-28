package com.global.hr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table("employees")   
public class Employee {

	@Id
	@Column("id")
	@JsonProperty("empId")
	private Long employeeId;
	
	@Column("name")
	@JsonProperty("empName")
	private String name;
	
	private Double salary;
	
	@Transient
	@JsonIgnore
	private String isCreate;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long employeeId, String name, Double salary, String isCreate) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
		this.isCreate = isCreate;
	}
	
	public Employee(Long employeeId, String name) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(String isCreate) {
		this.isCreate = isCreate;
	}

}
