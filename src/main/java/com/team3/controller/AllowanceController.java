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

import com.team3.model.APIResponse;
import com.team3.model.Allowance;
import com.team3.service.AllowanceService;
import com.team3.service.AllowanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/allowance")
public class AllowanceController {
	@Autowired
	private AllowanceService allowanceService;

	@PostMapping("/add")
	public void addDepart(@RequestBody Allowance allowance) {
		allowanceService.addAllowance(allowance);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody Allowance allowance) {
		allowanceService.editAllowance(allowance);
	}

	@DeleteMapping("/delete")
	public void deleteAllowance(@PathVariable Integer id) {
		allowanceService.deleteAllowance(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse getByCondition(@RequestBody Allowance allowance) {
		return allowanceService.getByCondition(allowance);
	}

}
