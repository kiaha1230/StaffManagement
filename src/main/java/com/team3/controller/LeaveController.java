package com.team3.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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

import com.team3.model.Leave;
import com.team3.model.Record;
import com.team3.resources.UserInformation;
import com.team3.Ultilities.Ultilities;
import com.team3.model.APIResponse;
import com.team3.model.Depart;
import com.team3.service.LeaveService;
import com.team3.service.LogAuditService;
import com.team3.service.RecordService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private LogAuditService logAuditService;
	@Autowired
	private RecordService recordService;

	@GetMapping("getsAllLeave")
	public ArrayList<Leave> getAllLeave() {
		return leaveService.getAllLeave();
	}

	@GetMapping("/leaves/{id}")
	public Optional<Leave> getById(@PathVariable int id) {
		return leaveService.getById(id);
	}

	@PostMapping("/add")
	public void addDepart(@RequestBody Leave leave) {
		LocalDate localDate = leave.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		leave.setAccept(1);
		leave.setStaffId(UserInformation.getACCOUNT().getStaffId());
		Boolean status = leaveService.isAnAnnualLeaveInMonth(leave.getStaffId(), month, year);
		if (status) {
			leave.setStatus(false);
		} else {
			leave.setStatus(true);
		}

		leaveService.addLeave(leave);
	}

	@PutMapping("/edit")
	public void editDepart(@RequestBody Leave leave) {
		leave.setAccept(1);
		if (leaveService.isAnAnnualLeaveInMonth(leave.getStaffId(), leave.getFromDate().getMonth(),
				leave.getFromDate().getYear())) {
			leave.setStatus(true);
		} else {
			leave.setStatus(false);
		}
		leaveService.editLeave(leave);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteLeave(@PathVariable Integer id) {
		logAuditService.deleteDiff(leaveService.getByIdSQL(id));
		leaveService.deleteLeave(id);
	}

	@PostMapping("/getsByConditions")
	public APIResponse findByCondition(@RequestBody Leave leave) {
		return leaveService.findByCondition(leave);
	}

	@PostMapping("/test")
	public void isAnnualLeaveInMonth(@RequestBody Leave leave) {
		leaveService.isAnAnnualLeaveInMonth(leave.getStaffId(), 12, 2019);
	}

	@PostMapping("/getsPendingByConditions")
	public APIResponse findByConditionPending(@RequestBody Leave leave) {
		return leaveService.findByConditionPending(leave);
	}

	@PutMapping("/deny")
	public void deny(@RequestBody Leave leave) {
		if ((leave.getFromDate().before(new Date()) == true) && (leave.getToDate().before(new Date()) == false)) {
			String fromDate = Ultilities.dateToStringUSFormat(leave.getFromDate());
			String nowDate = Ultilities.dateToStringUSFormat(new Date());
			LocalDate startDate = LocalDate.parse(fromDate);
			LocalDate endDate = LocalDate.parse(nowDate);
			Integer daysBetween = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
//			List<LocalDate> totalDates = LongStream.iterate(0, i -> i + 1).limit(daysBetween)
//					.mapToObj(i -> startDate.plusDays(i)).collect(Collectors.toList());
			for (int i = 0; i < daysBetween; i++) {
				Record record = new Record();
				record.setType(false);
				record.setReason("Nghỉ không phép");
				record.setStaffId(leave.getStaffId());
				record.setBonus(100000.0);
				recordService.addOrEditRecord(record);
			}
			leave.setAccept(0);
			leave.setStatus(false);
			leaveService.editLeave(leave);
		} else if ((leave.getFromDate().before(new Date()) == true) && (leave.getToDate().before(new Date()) == true)) {
			String fromDate = Ultilities.dateToStringUSFormat(leave.getFromDate());
			String nowDate = Ultilities.dateToStringUSFormat(leave.getToDate());
			LocalDate startDate = LocalDate.parse(fromDate);
			LocalDate endDate = LocalDate.parse(nowDate);
			Integer daysBetween = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
//			List<LocalDate> totalDates = LongStream.iterate(0, i -> i + 1).limit(daysBetween)
//					.mapToObj(i -> startDate.plusDays(i)).collect(Collectors.toList());
			for (int i = 0; i < daysBetween; i++) {
				Record record = new Record();
				record.setType(false);
				record.setReason("Nghỉ không phép");
				record.setStaffId(leave.getStaffId());
				record.setBonus(100000.0);
				recordService.addOrEditRecord(record);
			}
			leave.setAccept(0);
			leave.setStatus(false);
			leaveService.editLeave(leave);
		} else {
			leave.setAccept(0);
			leave.setStatus(false);
			leaveService.editLeave(leave);
		}

	}

	@PutMapping("/accept")
	public void accept(@RequestBody Leave leave) {
		leave.setAccept(2);
		leaveService.editLeave(leave);
	}

	@PostMapping("/getByStaffId")
	public APIResponse getByStaffId(@RequestBody Leave leave) {
		return leaveService.getByStaffId(leave);
	}
}
