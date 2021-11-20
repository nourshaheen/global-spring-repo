package com.global.hr.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.entity.Employee;
import com.global.hr.mapper.EmployeeMapper;
import com.global.hr.repository.EmployeeReps;

@Component
@Qualifier("employeeNamedParameterJDBCRepo")
public class EmployeeNamedParameterJDBCRepo implements EmployeeReps {
	
	@Autowired  // field injection by constructor
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;
	
// constructor injection 
//	public EmployeeNamedParameterJDBCRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//		super();
//		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//	}

	@Override
	public int count() {	
		
//		 namedParameterJdbcTemplate.queryForObject("select count(*) from employees", Integer.class);
		return 0;
	}

	@Override
	public Employee findById(Long id) {		
		return namedParameterJdbcTemplate.queryForObject("select id, name, salary from employees where id = :empId"
				, new MapSqlParameterSource("empId", id) , new EmployeeMapper());
	}
	
	@Override
	public List<Employee> findByNameAndSalary(String name, Double salary) {	
		
		MapSqlParameterSource mapParam = new MapSqlParameterSource();
		mapParam.addValue("name", name);
		mapParam.addValue("salary", salary);
		
		
		return namedParameterJdbcTemplate.query("select id, name, salary from employees where name = :name and salary = :salary"
				, mapParam , new EmployeeMapper());
	}

	@Override
	public List<Employee> findAll() {
		
//		 jdbcTemplate.query("select id, name, salary from employees" , new EmployeeMapper());
		return null ;
	}

	@Override
	public int insert(Employee employee) {
		
		return namedParameterJdbcTemplate.update("insert into employees (id, name, salary) values (:employeeId, :name, :salary)", 
				new BeanPropertySqlParameterSource(employee));
	}

	@Override
	public int update(Employee employee) {
		return namedParameterJdbcTemplate.update("update employees set name= :name , salary = :salary", 
				new BeanPropertySqlParameterSource(employee));
	}

	@Override
	public int delete(Long id) {		
		return namedParameterJdbcTemplate.update("delete from employees where id = :id", 
				new MapSqlParameterSource("id", id));
	}

	
	
//	@Autowired  //  setter injection 
//	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//	}

}
