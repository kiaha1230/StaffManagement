package com.team3.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.team3.customModel.StaffCustom;
import com.team3.model.Account;
import com.team3.model.Depart;
import com.team3.model.Staff;
import com.team3.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private StaffService staffService;

//	@GetMapping("staffs")
//	public ArrayList<Staff> getAllStaff() {
//
//		ArrayList<Staff> list = new ArrayList<Staff>();
//		list = staffService.getAllStaff();
//		return list;
//	}
//
//	@GetMapping("staffs/{id}")
//	public Optional<Staff> getById(@PathVariable int id) {
//		return staffService.getById(id);
//	}

	@PostMapping("/add")
	public void addTask(@RequestBody Staff staff) {
		staffService.addOrEditStaff(staff);
	}

	@PutMapping("/edit")
	public void editTask(@RequestBody Staff staff) {
		staffService.addOrEditStaff(staff);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTask(@PathVariable int id) {
		staffService.deleteStaff(id);
	}

	@PostMapping("/getsByConditions")
	public List<Staff> findByCondition(@RequestBody Staff staff) {
		return staffService.findByCondition(staff);
	}

	@PostMapping("/top10Staff")
	public List<Staff> top10Staff(@RequestBody Staff staff) {
		return staffService.top10Staff(staff);
	}

	@GetMapping("test")
	public String test()

	{
		return "test";
	}

}
