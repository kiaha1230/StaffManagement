package com.team3.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.team3.model.Leave;
import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.service.LeaveService;
import com.team3.service.LogAuditService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private LogAuditService logAuditService;

	@GetMapping("getsAllLeave")
	public ArrayList<Leave> getAllLeave() {
		return leaveService.getAllLeave();
	}

//	@GetMapping("/leaves/{id}")
//	public Optional<Leave> getById(@PathVariable int id) {
//		return leaveService.getById(id);
//	}

	@PostMapping("/add")
	public void addDepart(@RequestBody Leave leave) {
		logAuditService.addDiff(leave);
		leaveService.addLeave(leave);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody Leave leave) {
		logAuditService.getDiff(leaveService.getByIdSQL(leave.getId()), leave);
		leaveService.editLeave(leave);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteLeave(@PathVariable Integer id) {
		logAuditService.deleteDiff(leaveService.getByIdSQL(id));
		leaveService.deleteLeave(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse findByCondition(@RequestBody Leave leave) {
		return leaveService.findByCondition(leave);
	}

}
