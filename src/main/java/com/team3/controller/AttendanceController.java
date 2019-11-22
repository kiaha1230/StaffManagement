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

import com.team3.model.APIResponse;
import com.team3.model.Account;
import com.team3.model.Attendance;

import com.team3.service.AttendanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/get/{id}")
	public Optional<Attendance> getById(@PathVariable Integer id) {
		return attendanceService.getById(id);
	}

	@PostMapping("/add")
	public void addDepart(@RequestBody Attendance attendance) {
		attendanceService.addAttendance(attendance);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody Attendance attendance) {
		attendanceService.editAttendance(attendance);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAttendance(@PathVariable Integer id) {
		attendanceService.deleteAttendance(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse getByCondition(@RequestBody Attendance attendance) {
		return attendanceService.getByCondition(attendance);
	}

	@GetMapping("/getByStaffId/{staffId}")
	public Attendance getByStaffId(@PathVariable Integer staffId) {
		return attendanceService.getByStaffId(staffId);
	}

	@PostMapping("/checkIn")
	public void checkIn(@RequestBody Integer staffId) {
		attendanceService.checkIn(staffId);
	}

	@PostMapping("/checkOut")
	public void checkOut(@RequestBody Integer staffId) {
		attendanceService.checkOut(staffId);
	}

}
