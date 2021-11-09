package com.global;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class Employee {

	private Long id;
	private String name;
	private Double salary;

	private List<Address> address;
	
	private Department department;
	
//	private Department department2;

	public void init() {

		System.out.println("In Employee init method >>> ");
	}

	public void cleanup() {
		System.out.println("In Employee destroy method >>> ");
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, String name, Double salary, Department department, List<Address> address) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.department = department;
	}

	public void printName() {

		System.out.println("Employee name is >> " + name + " and salary is >>" + salary + " and department is "
				+ department.getName());

		for (Address address : address) {

			System.out.println("Address is >>" + address.getCity() + " and id is " + address.getId());

		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Department getDepartment() {
		return department;
	}

	@Autowired
//	@Qualifier("dept")
	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}


	
	

}
