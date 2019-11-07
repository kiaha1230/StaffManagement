package com.team3.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team3.model.Attendance;

import com.team3.service.AttendanceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/accounts")
	public void addDepart(@RequestBody Attendance attendance) {
		attendanceService.addAttendance(attendance);
	}

	@PutMapping("/accounts/update")
	public void editDepart(@RequestBody Attendance attendance) {
		attendanceService.editAttendance(attendance);
	}

	@DeleteMapping("/accounts/delete")
	public void deleteAttendance(@RequestBody Attendance attendance) {
		attendanceService.deleteAttendance(attendance.getId());
	}

	@PostMapping("/accounts/condition")
	public ArrayList<Attendance> getByCondition(@RequestBody Attendance attendance) {
		return attendanceService.getByCondition(attendance);
	}

}
