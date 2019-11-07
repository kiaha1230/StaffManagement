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

import com.team3.model.LogAudit;
import com.team3.service.LogAuditService;
import com.team3.service.LogAuditService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/logaudit")
public class LogAuditController {
	@Autowired
	private LogAuditService logAuditService;

	@PostMapping("/add")
	public void addDepart(@RequestBody LogAudit logAudit) {
		logAuditService.add(logAudit);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody LogAudit logAudit) {
		logAuditService.edit(logAudit);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteLogAudit(@PathVariable Integer id) {
		logAuditService.deleteLogAudit(id);
	}

	@PostMapping("/getsByConditions")
	public ArrayList<LogAudit> getByCondition(@RequestBody LogAudit logAudit) {
		return logAuditService.getByCondition(logAudit);
	}

}
