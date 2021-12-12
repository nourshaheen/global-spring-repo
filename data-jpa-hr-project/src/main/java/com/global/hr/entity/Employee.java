package com.global.hr.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Table(name = "actor")
@Entity
@NamedQuery(name = "Employee.findBySalary" , query = "select emp from Employee emp where emp.salary"
		+ " >= :salary and name like :name")

//@SqlResultSetMapping(
//        name = "empMapping",
//        entities = @EntityResult(
//                entityClass = Employee.class,
//                fields = {
//                    @FieldResult(name = "id", column = "emp_id"),
//                    @FieldResult(name = "name", column = "emp_name"),
//                    @FieldResult(name = "salary", column = "salary")}))
//
//@NamedNativeQuery(name = "Employee.findByDepartment" , query = "select emp.emp_id, emp.emp_name, emp.salary"
//		+ " from hr_employees emp where emp.department_id = :deptId", resultSetMapping = "empMapping")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;
	
	private String lastName;
	
	private Date lastupdate;

	private Double salary;

	@ManyToOne()
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
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

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
