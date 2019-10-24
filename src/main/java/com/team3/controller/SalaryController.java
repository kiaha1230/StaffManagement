package com.team3.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.customModel.AccountCustom;
import com.team3.customModel.SalaryCustom;
import com.team3.model.Account;
import com.team3.model.Salary;
import com.team3.service.SalaryService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/salary")
public class SalaryController {
	@Autowired
	private SalaryService salaryService;

//	@GetMapping("salaries")
//	public ArrayList<Salary> getAllSalary() {
//		return salaryService.getAllSalary();
//	}
//
//	@GetMapping("/salaries/{id}")
//	public Optional<Salary> getById(@PathVariable int id) {
//		return salaryService.getById(id);
//	}

	@PostMapping("/salaries")
	public void addDepart(@RequestBody Salary salary) {
		salaryService.addOrEditSalary(salary);
	}

	@PutMapping("/salaries/{id}")
	public void editDepart(@RequestBody Salary salary) {
		salaryService.addOrEditSalary(salary);
	}

	@DeleteMapping("/salaries/{id}")
	public void deleleSalary(@PathVariable int id) {
		salaryService.deleteSalary(id);
	}

	@PostMapping("/salaries/condition")
	public ArrayList<SalaryCustom> getByCondition(@RequestBody SalaryCustom salaryCustom) {
		return salaryService.findByCondition(salaryCustom);
	}

}
