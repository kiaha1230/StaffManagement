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
import com.team3.model.Depart;
import com.team3.model.Record;
import com.team3.service.RecordService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private RecordService recordService;

	@GetMapping("records")
	public ArrayList<Record> getAllRecord() {
		return recordService.getAllRecord();
	}

	@GetMapping("records/{id}")
	public Optional<Record> getById(@PathVariable int id) {
		return recordService.getById(id);
	}

	@PostMapping("/records")
	public void addRecord(@RequestBody Record record) {
		recordService.addOrEditRecord(record);
	}

	@PutMapping("/records/{id}")
	public void editProject(@RequestBody Record record) {
		recordService.addOrEditRecord(record);
	}

	@DeleteMapping("/records/{id}")
	public void deleteRecord(@PathVariable Integer id) {
		recordService.deleteRecord(id);
	}

	@PostMapping("/records/condition/")
	public List<RecordCustom> findByCondition(@RequestBody Record record) {
		return recordService.findByCondition(record);
	}
}
