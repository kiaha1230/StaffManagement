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

import com.team3.customModel.RecordCustom;
import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.model.Record;
import com.team3.model.Staff;
import com.team3.service.RecordService;
import com.team3.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private RecordService recordService;
	@Autowired
	private StaffService staffService;

//	@GetMapping("records")
//	public ArrayList<Record> getAllRecord() {
//		return recordService.getAllRecord();
//	}
//
//	@GetMapping("records")
//	public Optional<Record> getById(@PathVariable int id) {
//		return recordService.getById(id);
//	}

	@PostMapping("/add")
	public void addRecord(@RequestBody Record record) {
		recordService.addOrEditRecord(record);
	}

	@PutMapping("/edit")
	public void editProject(@RequestBody Record record) {
		recordService.addOrEditRecord(record);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteRecord(@PathVariable Integer id) {
		recordService.deleteRecord(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse findByCondition(@RequestBody Record record) {
		return recordService.findByCondition(record);
	}
	
	// test khong check in trong 24h

//	@GetMapping("/test")
//	public void test() {
//		List<Staff> list = staffService.getAllStaff();
//		for (Staff s : list) {
//			recordService.noCheckInNoLeave(s.getId());
//		}
//	}
}
