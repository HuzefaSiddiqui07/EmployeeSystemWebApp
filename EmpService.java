package com.employee.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.dao.EmpRepo;
import com.employee.model.Employee;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;
	
	// Add / Save / POST Method
	public void addEmp(Employee e) {
		
		repo.save(e);
	}

	// GET / Retrieve Method (All Data )
	public List<Employee> getAllEmp() {
		
		return repo.findAll();
		
	}
	
	// GET / Retrieve Method By I'd
	public Employee getEMpById(int id) {
		Optional<Employee> e = repo.findById(id);
		if (e.isPresent()) {
			
			return e.get();
			
		}
		
		return null;
		
	}
	
	// DELETE Method
	public void deleteEMp(int id) {
		
		repo.deleteById(id);
		
	}
	
	public Page<Employee> getEMpByPaginate(int currentPage , int size) {
		
		Pageable p = PageRequest.of(currentPage, size);
		return repo.findAll(p);
	
	}

}
