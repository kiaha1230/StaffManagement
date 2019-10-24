package com.team3.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.Attendance;
import com.team3.repository.AttendanceRepository;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public ArrayList<Attendance> getAllAttendance() {
		return (ArrayList<Attendance>) attendanceRepository.findAll();
	}

}
