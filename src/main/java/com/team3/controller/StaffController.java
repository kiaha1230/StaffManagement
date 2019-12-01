package com.team3.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import com.team3.customModel.StaffCustom;
import com.team3.model.APIResponse;
import com.team3.model.Account;
import com.team3.model.Depart;
import com.team3.model.Staff;
import com.team3.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private StaffService staffService;

	@GetMapping("staffs")
	public ArrayList<Staff> getAllStaff() {

		ArrayList<Staff> list = new ArrayList<Staff>();
		list = staffService.getAllStaff();
		return list;
	}

//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		return new MultipartConfigElement("");
//	}
//
//	@Bean
//	public MultipartResolver multipartResolver() {
//		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(1000000);
//		return multipartResolver;
//	}

//
	@GetMapping("get/{id}")
	public Optional<Staff> getById(@PathVariable int id) {
		return staffService.getById(id);
	}

	@PostMapping("/add")
	public void addTask(@RequestParam("staff") Staff staff, @RequestParam("multipartFile") MultipartFile multipartFile) {
		staff.setPhoto(multipartFile.getResource().getFilename());
		staffService.addOrEditStaff(staff);
	}

	@PutMapping("/edit")
	public void editTask(@RequestBody Staff staff) {
		staffService.addOrEditStaff(staff);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTask(@PathVariable int id) {
		staffService.deleteStaff(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse findByCondition(@RequestBody Staff staff) {
		return staffService.findByCondition(staff);
	}

	@GetMapping("/getsTop")
	public List<Staff> top10Staff() {
		return staffService.top10Staff();
	}

	@GetMapping("/getsStaffActive")
	public List<Staff> getsStaffActive() {
		return staffService.getByActive();
	}

	@GetMapping("/getsStaffWithoutAccount")
	public List<Staff> getStaffsWithoutAccount() {
		return staffService.getsStaffWithoutAccount();
	}

	@PostMapping("/getBirthdaysInMonth")
	public List<Staff> getBirthdaysInMonth() {
		return staffService.getStaffHaveBirthday();
	}

	@GetMapping("/getsStaffWithoutSalary")
	public List<Staff> getStaffswithoutSalary() {
		return staffService.getsStaffWithoutSalary();
	}

	@GetMapping("/getsStaffWithoutAllowance")
	public List<Staff> getsStaffWithoutAllowance() {
		return staffService.getsStaffWithoutAllowance();
	}

}
