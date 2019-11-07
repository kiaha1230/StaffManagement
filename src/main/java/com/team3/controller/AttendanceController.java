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
import com.team3.model.Attendance;

import com.team3.service.AttendanceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;

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
	public ArrayList<Attendance> getByCondition(@RequestBody Attendance attendance) {
		return attendanceService.getByCondition(attendance);
	}

}
