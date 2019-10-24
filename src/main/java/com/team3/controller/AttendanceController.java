package com.team3.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("attendances")
	public ArrayList<Attendance> getAllAttendance() {
		return attendanceService.getAllAttendance();
	}

}
