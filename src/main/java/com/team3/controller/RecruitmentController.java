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

import com.team3.model.APIResponse;
import com.team3.model.Recruitment;
import com.team3.service.RecruitmentService;
import com.team3.service.RecruitmentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recruitment")
public class RecruitmentController {
	@Autowired
	private RecruitmentService recruitmentService;

//	@GetMapping("departs")
//	public ArrayList<Recruitment> getAllRecruitment() {
//		return ((RecruitmentService) recruitmentService).getAllRecruitment();
//	}
//
//	@GetMapping("/departs/{id}")
//	public Optional<Recruitment> getById(@PathVariable int id) {
//		return recruitmentService.getById(id);
//	}

	@PostMapping("/add")
	public void addRecruitment(@RequestBody Recruitment recruitment) {
		recruitmentService.addOrEditRecruitment(recruitment);
	}

	@PutMapping("/edit")
	public void editRecruitment(@RequestBody Recruitment recruitment) {
		recruitmentService.addOrEditRecruitment(recruitment);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteRecruitment(@PathVariable Integer id) {
		recruitmentService.deleteRecruitment(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse findByCondition(@RequestBody Recruitment recruitment) {
		return recruitmentService.findByCondition(recruitment);
	}

	@PostMapping("/checkRecruitmentCode")
	public Boolean checkStaffCode(@RequestBody String recruitmentCode) {
		return recruitmentService.checkRecruitmentCodeDuplicate(recruitmentCode.trim());
	}

}
