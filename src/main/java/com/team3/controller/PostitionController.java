package com.team3.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.team3.model.Position;
import com.team3.service.LogAuditService;
import com.team3.service.PositionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/position")
public class PostitionController {
	@Autowired
	private PositionService positionService;
	@Autowired
	private LogAuditService logAuditService;

	@PostMapping("/add")
	public void addDepart(@RequestBody Position position) {
		logAuditService.addDiff(position);
		positionService.addPosition(position);

	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody Position position) {
		positionService.editPosition(position);
	}

	@DeleteMapping("/delete/{id}")
	public void deletePosition(@PathVariable Integer id) {

		positionService.deletePosition(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse getByCondition(@RequestBody Position position) {
		return positionService.findByCondition(position);
	}

	@GetMapping("/getsAllPosition")
	public ArrayList<Position> getAllPosition() {
		return positionService.getAllPosition();
	}

}
