package com.team3.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.model.Allowance;
import com.team3.service.AllowanceService;
import com.team3.service.AllowanceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/allowance")
public class AllowanceController {
	@Autowired
	private AllowanceService allowanceService;

	@PostMapping("/allowances")
	public void addDepart(@RequestBody Allowance allowance) {
		allowanceService.addAllowance(allowance);
	}

	@PutMapping("/allowances/update")
	public void editDepart(@RequestBody Allowance allowance) {
		allowanceService.editAllowance(allowance);
	}

	@DeleteMapping("/allowances/delete")
	public void deleteAllowance(@RequestBody Allowance allowance) {
		allowanceService.deleteAllowance(allowance.getId());
	}

	@PostMapping("/allowances/condition")
	public ArrayList<Allowance> getByCondition(@RequestBody Allowance allowance) {
		return allowanceService.getByCondition(allowance);
	}

}
