package com.team3.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.s;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.APIResponse;
import com.team3.model.Payroll;
import com.team3.model.Salary;
import com.team3.model.Depart;
import com.team3.model.Pager;
import com.team3.model.Staff;
import com.team3.repository.AllowanceRepository;
import com.team3.repository.LeaveRepository;
import com.team3.repository.PayrollRepository;
import com.team3.repository.RecordRepository;
import com.team3.repository.SalaryRepository;
import com.team3.repository.StaffRepository;
import com.team3.resources.UserInformation;

@Service
public class PayrollService {
	@Autowired
	private PayrollRepository payrollRepository;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private SalaryRepository salaryRepository;
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private AllowanceRepository allowanceRepository;
	@Autowired
	private RecordRepository recordRepository;

	public ArrayList<Payroll> getAllPayroll() {
		List<Payroll> list = new ArrayList<Payroll>();
		list = payrollRepository.findAll();
		return (ArrayList<Payroll>) list;
	}

	public Optional<Payroll> getById(int id) {
		return payrollRepository.findById(id);
	}

	public void editPayroll(Payroll payroll) {
		payrollRepository.save(payroll);

	}

	public void deletePayroll(int id) {
		payrollRepository.deleteById(id);
	}

	public Payroll getByIdSQL(Integer id) {
		Payroll payroll = new Payroll();
		String query = "from Payroll where id = :id ";
		Query q = em.createQuery(query);
		q.setParameter("id", id);
		payroll = (Payroll) q.getSingleResult();
		return payroll;

	}

	public void addPayroll(Payroll payroll) {
		payrollRepository.save(payroll);
	}

	public APIResponse getByConditionPager(Payroll payroll) {
		ArrayList<Payroll> list = new ArrayList<Payroll>();
		Integer month = payroll.getDatetime().getMonth();
		Integer year = payroll.getDatetime().getYear();
		String query = "select p.id, p.staffId,s.staffName, p.datetime, p.grossSal, p.netSal, p.bonus, p.leaveDate , p.allowance, p.netPay, s.staffCode   from Payroll p , Staff s where p.staffId = s.id ";

		if (!(payroll.getStaffId() == null)) {
			query += " and  p.staffId = :staffId";
		}

		if (payroll.getMonth() != null) {
			query += " and month(p.datetime) = :month ";
		}
		if (payroll.getYear() != null) {
			query += " and year(p.datetime) = :year ";
		}
		if (payroll.getFromNetPay() != null && payroll.getToNetPay() != null) {
			query += " and a.netPay between :from and :to ";
		}
		if (payroll.getFromNetPay() != null && payroll.getToNetPay() == null) {
			query += " and a.netPay >= :from ";
		}
		if (payroll.getFromNetPay() == null && payroll.getToNetPay() != null) {
			query += " and a.netPay <= :to ";
		}

		Query q = em.createQuery(query);

		// set parameter

		if (!(payroll.getStaffId() == null)) {
			q.setParameter("staffId", payroll.getStaffId());
		}
		if (payroll.getMonth() != null) {
			q.setParameter("month", month);
		}
		if (payroll.getYear() != null) {
			q.setParameter("year", year);
		}
		if (payroll.getFromNetPay() != null && payroll.getToNetPay() != null) {
			q.setParameter("from", payroll.getFromNetPay());
			q.setParameter("to", payroll.getToNetPay());
		}
		if (payroll.getFromNetPay() != null && payroll.getToNetPay() == null) {
			q.setParameter("from", payroll.getFromNetPay());
		}
		if (payroll.getFromNetPay() == null && payroll.getToNetPay() != null) {
			q.setParameter("to", payroll.getToNetPay());
		}
		// api response

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(payroll.getPager().getPage() * payroll.getPager().getPageSize());
		q.setMaxResults(payroll.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Payroll custom = new Payroll();
			custom.setId((Integer) record[0]);
			custom.setStaffId((Integer) record[1]);
			custom.setStaffName(record[2].toString());
			custom.setDatetime((Date) record[3]);
			custom.setGrossSal((Double) record[4]);
			custom.setNetSal((Double) record[5]);
			custom.setBonus((Double) record[6]);
			custom.setLeaveDate((Integer) record[7]);
			custom.setAllowance((Double) record[8]);
			custom.setNetPay((Double) record[9]);
			custom.setStaffCode((String) record[10]);
			list.add(custom);
		});

		pager.setPageSize(payroll.getPager().getPageSize());
		pager.setPage(payroll.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

//	public List<Payroll> getPayrollByStaff() {
//		List<Payroll> list = new ArrayList<Payroll>();
//		String hql = "select a.id,s.staffName,s.staffCode  from Staff s, Payroll a where s.id = a.staffId ";
//		Query q = em.createQuery(hql);
//		List<Object[]> obj = q.getResultList();
//		obj.stream().forEach((record) -> {
//			Payroll custom = new Payroll();
//			custom.setId((Integer) record[0]);
//			custom.setStaffName(record[1].toString());
//			custom.setStaffCode(record[2].toString());
//			list.add(custom);
//		});
//		return list;
//	}

	public Integer getWorkingDayOfMonth(Integer month, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		List<Date> count = new ArrayList<Date>();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int myMonth = calendar.get(Calendar.MONTH);
		while (myMonth == calendar.get(Calendar.MONTH)) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			if (calendar.getTime().getDay() != 0 && calendar.getTime().getDay() != 6
					&& calendar.getTime().getMonth() == month) {
				count.add(calendar.getTime());
			}
		}
		return count.size();

	}

}
