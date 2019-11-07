package com.team3.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.Attendance;
import com.team3.model.Leave;
import com.team3.repository.LeaveRepository;

@Service
public class LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<Leave> getAllLeave() {
		List<Leave> list = new ArrayList<Leave>();
		list = leaveRepository.findAll();
		return (ArrayList<Leave>) list;
	}

	public Optional<Leave> getById(int id) {
		return leaveRepository.findById(id);
	}

	public void addLeave(Leave leave) {
		leaveRepository.save(leave);
	}

	public void editLeave(Leave leave) {
		leaveRepository.save(leave);
	}

	public void deleteLeave(int id) {
		leaveRepository.deleteById(id);
	}

	public ArrayList<Leave> findByCondition(Leave leave) {
		ArrayList<Leave> list = new ArrayList<Leave>();
		String query = "select l.id , s.staffName , l.date , l.reason, l.status from Leave l , Staff s where l.staffId = s.id ";
		if (leave.getStaffId() != null) {
			query += " and  l.staffId = :staffId ";
		}
		if (leave.getStatus() != null) {
			query += " and  l.status = :status ";
		}
		if (leave.getToDate() != null && leave.getFromDate() != null) {
			query += " and l.date between :fromDate and :toDate ";
		}
		if (leave.getToDate() != null && leave.getFromDate() == null) {
			query += " and l.date >= :fromDate ";
		}
		if (leave.getToDate() == null && leave.getFromDate() != null) {
			query += " and l.date <= :fromDate ";
		}
		Query q = em.createQuery(query);

		if (leave.getStaffId() != null) {
			q.setParameter("staffId", leave.getStaffId());
		}
		if (leave.getStatus() != null) {
			q.setParameter("status", leave.getStatus());
		}
		if (leave.getStaffId() != null) {
			q.setParameter("staffId", leave.getStaffId());
		}
		if (leave.getToDate() != null && leave.getFromDate() != null) {
			q.setParameter("fromDate", leave.getFromDate());
			q.setParameter("toDate", leave.getToDate());
		}
		if (leave.getToDate() != null && leave.getFromDate() == null) {
			q.setParameter("fromDate", leave.getFromDate());
		}
		if (leave.getToDate() == null && leave.getFromDate() != null) {
			q.setParameter("toDate", leave.getToDate());
		}

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Leave custom = new Leave();
			custom.setId((Integer) record[0]);
			custom.setStaffName(record[1].toString());
			custom.setDate((Date) record[2]);
			custom.setReason((String) record[3]);
			custom.setStatus( (Boolean) record[4]);
			list.add(custom);
		});
		return list;
	}
}
