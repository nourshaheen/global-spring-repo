package com.global.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeReps;

@Component
public class StartupProject implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("employeeJDBCRepo")
	private EmployeeReps employeeReps;

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.execute("DROP TABLE IF EXISTS employees");

		jdbcTemplate.execute("CREATE TABLE employees(id SERIAL, name VARCHAR(255), salary NUMERIC(15, 2))");

		if (employeeReps.count() == 0) {
			employeeReps.insert(new Employee(20L, "Mohamed", 5221.0));
			employeeReps.insert(new Employee(30L, "Nour", 8569.0));
			employeeReps.insert(new Employee(40L, "Ali", 5521.0));
		}
	}

}
