package com.employee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.model.Employee;
import com.employee.service.EmpService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService employeeService;  // Secondary DataType
	
	@GetMapping("/")
	public String home(Model m) {
		
		return findPaginated(0 , m);
		
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		
		return "add_emp";
		
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e , HttpSession session) {
		
		employeeService.addEmp(e);
		session.setAttribute("msg", "Employee Added Sucessfully...");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id , Model m) {
		Employee e = employeeService.getEMpById(id);
		m.addAttribute("emp" , e);
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e , HttpSession session) {
		employeeService.addEmp(e);
		session.setAttribute("msg", "Emp Data Update Sucessfully...");
		return "redirect:/";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id , HttpSession session) {
		employeeService.deleteEMp(id);
		session.setAttribute("msg", "Emp Data Delete Sucessfully...");
		return "redirect:/";
		
	}

	@GetMapping("/page/{pageno}")
	private String findPaginated(@PathVariable int pageno, Model m) {
		Page<Employee> empList = employeeService.getEMpByPaginate(pageno, 2);
		m.addAttribute("emp", empList);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages" , empList.getTotalPages());
		m.addAttribute("totalItem" , empList.getTotalElements());
		return "index";
	}
	
}
