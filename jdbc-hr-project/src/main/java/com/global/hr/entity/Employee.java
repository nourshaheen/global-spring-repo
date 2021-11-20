package com.global.hr.entity;

public class Employee {

	private Long employeeId;
	private String name;
	private Double salary;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long employeeId, String name, Double salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
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

}
