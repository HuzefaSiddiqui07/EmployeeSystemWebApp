package com.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{
	
	
}
