package com.team3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.model.Depart;
import com.team3.service.DepartService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/depart")
public class DepartController {
	@Autowired
	private DepartService departService;

//	@GetMapping("departs")
//	public ArrayList<Depart> getAllDepart() {
//		return ((DepartService) departService).getAllDepart();
//	}
//
//	@GetMapping("/departs/{id}")
//	public Optional<Depart> getById(@PathVariable int id) {
//		return departService.getById(id);
//	}

	@PostMapping("/departs")
	public void addDepart(@RequestBody Depart depart) {
		departService.addDepart(depart);
	}

	@PutMapping("/departs/{id}")
	public void editDepart(@RequestBody Depart depart) {
		departService.editDepart(depart);
	}

	@DeleteMapping("/departs/{id}")
	public void deleteDepart(@PathVariable int id) {
		departService.deleteDepart(id);
	}

	@PostMapping("/departs/condition/")
	public List<Depart> findByCondition(@RequestBody Depart depart) {
		return departService.findByCondition(depart);
	}

}
