package com.team3.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
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
import com.team3.model.Allowance;
import com.team3.model.Attendance;
import com.team3.model.Payroll;
import com.team3.model.Record;
import com.team3.model.Salary;
import com.team3.model.Staff;
import com.team3.model.Depart;
import com.team3.model.Leave;
import com.team3.model.Pager;
import com.team3.service.PayrollService;
import com.team3.service.RecordService;
import com.team3.service.SalaryService;
import com.team3.service.StaffService;
import com.team3.service.AllowanceService;
import com.team3.service.AttendanceService;
import com.team3.service.LeaveService;
import com.team3.service.LogAuditService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payroll")
@EnableScheduling
@Transactional
public class PayrollController {
	@Autowired
	private PayrollService payrollService;
	@Autowired
	private LogAuditService logAuditService;
	@Autowired
	private SalaryService salaryService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private RecordService recordService;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private AllowanceService allowanceService;

//	@GetMapping("/payrolls/{id}")
//	public Optional<Payroll> getById(@PathVariable int id) {
//		return payrollService.getById(id);
//	}
//

	@Scheduled(cron = "0 0 23 28-31 * ?")
	@GetMapping("/getpayroll")
	public void addPayroll() {
		final Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
			try {
				Date date = new Date();
				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int year = localDate.getYear();
				int month = localDate.getMonthValue();
//			int day = localDate.getDayOfMonth();
				Integer workingDays = payrollService.getWorkingDayOfMonth();
				List<Payroll> listPayroll = new ArrayList<Payroll>();
				List<Staff> listStaff = staffService.getAllStaff();
				for (Staff s : listStaff) {
					Payroll payroll = new Payroll();
					payroll.setStaffId(s.getId());
					payroll.setDatetime(new Date());
					// get sal and add to GROSS_SAL, NET_SAL
					Salary salary = new Salary();
					salary = salaryService.getByStaffId(s.getId());
					if (salary == null) {
						payroll.setGrossSal((double) 0);
						payroll.setNetSal((double) 0);
					} else {
						payroll.setGrossSal(salary.getGrossSalary());
						payroll.setNetSal(salary.getNetSalary());
					}

					// bonus
					List<Record> records = new ArrayList<Record>();
					records = recordService.getByStaffId(s.getId(), month, year);
					Double bonus = (double) 0;
					if (records == null) {
						bonus += 0;
					} else {
						for (Record r : records) {
							if (r.getType() == true) {
								bonus += r.getBonus();
							} else {
								bonus -= r.getBonus();
							}
						}
					}
					payroll.setBonus(bonus);
					// get leave date
					Integer staffWorkingDays = attendanceService.getWorkingDayOfStaff(s.getId(), month, year);
					Integer leaveDate = workingDays - staffWorkingDays;
					payroll.setLeaveDate(leaveDate);
					Double moneyPerDay = payroll.getGrossSal() / workingDays;
					Boolean isAnAnnualLeave = leaveService.isAnAnnualLeaveInMonth(s.getId(), month, year);
					Double annualSal = (double) 0;
					if (isAnAnnualLeave) {
						annualSal += moneyPerDay;
					}
					Double netMoneyCount = salaryService
							.taxNInsuranceCount(payroll.getGrossSal() - moneyPerDay * leaveDate + annualSal);

					// get Allowance

					Allowance allowance = new Allowance();
					allowance = allowanceService.getByIdSQL(s.getId());
					Double allowanceMoney = (double) 0;
					if (allowance == null) {
						allowanceMoney += 0;
						payroll.setAllowance(allowanceMoney);
					} else {
						allowanceMoney = allowance.getTravelAllowance() + allowance.getDeviceAllowance()
								+ allowance.getMealAllowance();
						payroll.setAllowance(allowanceMoney);
					}
					System.out.println("done");
					// set NetPay
					Double netPay = netMoneyCount + bonus + allowanceMoney;
					payroll.setNetPay(netPay);

					// add payroll to list
					listPayroll.add(payroll);

				}

				// add Payroll
				for (Payroll p : listPayroll) {
					payrollService.addPayroll(p);
				}
				System.out.println("done");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

//
//	@PutMapping("/edit")
//	public void editPayroll(@RequestBody Payroll payroll) {
//		logAuditService.getDiff(payrollService.getByIdSQL(payroll.getId()), payroll);
//		payrollService.editPayroll(payroll);
//
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public void deletePayroll(@PathVariable Integer id) {
//		logAuditService.deleteDiff(payrollService.getByIdSQL(id));
//		payrollService.deletePayroll(id);
//
//	}
//
	@PostMapping("/getsByConditions")
	public APIResponse getByConditionPager(@RequestBody Payroll payroll) {
		return payrollService.getByConditionPager(payroll);
	}

//
////
////	@GetMapping("/getPayrollByStaff")
////	public List<Payroll> getPayrollByStaff() {
////		return payrollService.getPayrollByStaff();
////	}
	@GetMapping("/test")
	public Integer getWorkingDayOfMonth(Integer month, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		List<Date> count = new ArrayList<Date>();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int myMonth = calendar.get(Calendar.MONTH);
		while (myMonth == calendar.get(Calendar.MONTH)) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			if (calendar.getTime().getDay() != 0 && calendar.getTime().getDay() != 6) {
				count.add(calendar.getTime());
			}
		}
		return count.size();

	}

	@GetMapping("/addtest")
	public void addtest() {
		Payroll payroll = new Payroll();
		payroll.setStaffId(25);
		payroll.setDatetime(new Date());
		payroll.setGrossSal(1000.0);
		payroll.setNetSal(2000.0);
		payroll.setBonus(1000.0);
		payroll.setLeaveDate(6);
		payroll.setAllowance(1000.0);
		payroll.setNetPay(5000.0);
		payrollService.addPayroll(payroll);
	}
}
