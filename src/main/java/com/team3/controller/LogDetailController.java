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

import com.team3.model.LogDetail;
import com.team3.service.LogDetailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/logdetail")
public class LogDetailController {
	@Autowired
	private LogDetailService logDetailService;

	@PostMapping("/add")
	public void addDepart(@RequestBody LogDetail logDetail) {
		logDetailService.addLogDetail(logDetail);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody LogDetail logDetail) {
		logDetailService.editLogDetail(logDetail);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteLogDetail(@PathVariable Integer id) {
		logDetailService.deleteLogDetail(id);
	}

	@PostMapping("/getsByConditions")
	public ArrayList<LogDetail> getByCondition(@RequestBody LogDetail logDetail) {
		return logDetailService.getByCondition(logDetail);
	}

}
