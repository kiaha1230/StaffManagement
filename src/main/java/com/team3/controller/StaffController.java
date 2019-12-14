package com.team3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.swing.text.TabableView;

import org.checkerframework.checker.units.qual.m2;
import org.checkerframework.checker.units.qual.s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import com.team3.Ultilities.Ultilities;
import com.team3.customModel.StaffCustom;
import com.team3.model.APIResponse;
import com.team3.model.Account;
import com.team3.model.Depart;
import com.team3.model.LogAudit;
import com.team3.model.LogDetail;
import com.team3.model.Staff;
import com.team3.resources.UserInformation;
import com.team3.service.LogAuditService;
import com.team3.service.LogDetailService;
import com.team3.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private StaffService staffService;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private LogAuditService logAuditService;
	@Autowired
	private LogDetailService logDetailService;

	@GetMapping("staffs")
	public ArrayList<Staff> getAllStaff() {

		ArrayList<Staff> list = new ArrayList<Staff>();
		list = staffService.getAllStaff();
		return list;
	}

	@GetMapping("get/{id}")
	public Optional<Staff> getById(@PathVariable int id) {
		return staffService.getById(id);
	}

//	@PostMapping("/add")
//	public void addTask(@RequestParam("staffCode") String staffCode, @RequestParam("staffName") String staffName,
//			@RequestParam("departId") String departId, @RequestParam("gender") String gender,
//			@RequestParam("birthday") String birthday, @RequestParam("email") String email,
//			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("positionId") String positionId,
//			@RequestParam("address") String address, @RequestParam("photoObj") MultipartFile photoObj) {
//		File file = new File("");
//		String currentDirectory = file.getAbsolutePath();
//		Staff staff = new Staff();
//		staff.setStatus(true);
//		staff.setStaffCode(staffCode);
//		staff.setStaffName(staffName);
//		staff.setDepartId(Integer.valueOf(departId));
//		staff.setGender(Boolean.valueOf(gender));
//		staff.setBirthday(Ultilities.stringToDate(birthday));
//		staff.setEmail(email);
//		staff.setPhoneNumber(phoneNumber);
//		staff.setPositionId(Integer.valueOf(positionId));
//		staff.setAddress(address);
//		String photoPath = file.getAbsolutePath() + "\\src\\images\\" + photoObj.getOriginalFilename();
//		try {
//			photoObj.transferTo(new File(photoPath));
//			staff.setPhoto(photoObj.getOriginalFilename());
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		staffService.addOrEditStaff(staff);
//	}

	@PostMapping("/add")
	public void addTask(@RequestBody Staff staff) {
		staff.setStatus(true);
		logAuditService.addDiff(staff);
		staffService.addOrEditStaff(staff);
	}

	@PutMapping("/testPhoto")
	public void testPhoto(@RequestParam("photoObj") MultipartFile photoObj, @RequestParam("staffId") String staffId) {
		File file = new File("");
		String currentDirectory = file.getAbsolutePath() + "\\src\\main\\resources\\static\\images\\";
		String ok = currentDirectory + photoObj.getOriginalFilename();
		try {
			photoObj.transferTo(new File(ok));
			Staff staff = staffService.getbyIdHQL(Integer.valueOf(staffId));
			staff.setPhoto(photoObj.getOriginalFilename());
			staffService.addOrEditStaff(staff);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PutMapping("/edit")
	public void editTask(@RequestBody Staff staff) {
		logAuditService.getDiff(staffService.getbyIdHQL(staff.getId()), staff);
		staffService.addOrEditStaff(staff);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTask(@PathVariable int id) {
		Staff staff = staffService.getbyIdHQL(id);
		staff.setStatus(false);
		LogAudit audit = new LogAudit();
		audit.setAccountId(UserInformation.getACCOUNT().getId());
		audit.setTableName("STAFF");
		audit.setActionDatetime(new Date());
		audit.setActionType(2);
		logAuditService.add(audit);
		LogDetail detail = new LogDetail();
		detail.setColumnName("STATUS");
		detail.setOldValue("TRUE");
		detail.setNewValue("FALSE");
		detail.setLogAuditId(audit.getId());
		logDetailService.addLogDetail(detail);
		staffService.addOrEditStaff(staff);
	}

	@PostMapping("/getsByConditions")
	public APIResponse findByCondition(@RequestBody Staff staff) {
		return staffService.findByCondition(staff);
	}

	@GetMapping("/getsTop")
	public List<StaffCustom> top10Staff() {
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

	@GetMapping("/getBirthdaysInMonth")
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

	@PostMapping("/checkStaffCode")
	public Boolean checkStaffCode(@RequestBody String staffCode) {
		return staffService.checkStaffCodeDuplicate(staffCode.trim());
	}

	@GetMapping("/getStaffCode")
	public List<String> getListStaffCodes() {
		return staffService.getListStaffCodes();

	}

}
