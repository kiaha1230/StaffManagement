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

import com.team3.model.APIResponse;
import com.team3.model.Attendance;
import com.team3.model.Leave;
import com.team3.model.Pager;
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

//	public ArrayList<Leave> findByCondition(Leave leave) {
//		ArrayList<Leave> list = new ArrayList<Leave>();
//		String query = "select l.id , s.staffName , l.leaveDate , l.reason, l.status from Leave l , Staff s where l.staffId = s.id ";
//		if (leave.getStaffId() != null) {
//			query += " and  l.staffId = :staffId ";
//		}
//		if (leave.getStatus() != null) {
//			query += " and  l.status = :status ";
//		}
//		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() != null) {
//			query += " and l.leaveDate between :fromDate and :toDate ";
//		}
//		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() == null) {
//			query += " and l.leaveDate <= :toDate ";
//		}
//		if (leave.getToLeaveDate() == null && leave.getFromLeaveDate() != null) {
//			query += " and l.leaveDate >= :fromDate ";
//		}
//		Query q = em.createQuery(query);
//
//		if (leave.getStaffId() != null) {
//			q.setParameter("staffId", leave.getStaffId());
//		}
//		if (leave.getStatus() != null) {
//			q.setParameter("status", leave.getStatus());
//		}
//		if (leave.getStaffId() != null) {
//			q.setParameter("staffId", leave.getStaffId());
//		}
//		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() != null) {
//			q.setParameter("fromDate", leave.getFromLeaveDate());
//			q.setParameter("toDate", leave.getToLeaveDate());
//		}
//		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() == null) {
//			q.setParameter("toDate", leave.getToLeaveDate());
//		}
//		if (leave.getToLeaveDate() == null && leave.getFromLeaveDate() != null) {
//			q.setParameter("fromDate", leave.getFromLeaveDate());
//		}
//
//		List<Object[]> obj = q.getResultList();
//		obj.stream().forEach((record) -> {
//			Leave custom = new Leave();
//			custom.setId((Integer) record[0]);
//			custom.setStaffName(record[1].toString());
//			custom.setLeaveDate((Date) record[2]);
//			custom.setReason((String) record[3]);
//			custom.setStatus( (Boolean) record[4]);
//			list.add(custom);
//		});
//		return list;
//	}

	// API

	public APIResponse findByCondition(Leave leave) {
		ArrayList<Leave> list = new ArrayList<Leave>();
		String query = "select l.id , s.staffName , l.leaveDate , l.reason, l.status from Leave l , Staff s where l.staffId = s.id ";
		if (leave.getStaffId() != null) {
			query += " and  l.staffId = :staffId ";
		}
		if (leave.getStatus() != null) {
			query += " and  l.status = :status ";
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() != null) {
			query += " and l.leaveDate between :fromDate and :toDate ";
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() == null) {
			query += " and l.leaveDate <= :toDate ";
		}
		if (leave.getToLeaveDate() == null && leave.getFromLeaveDate() != null) {
			query += " and l.leaveDate >= :fromDate ";
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
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() != null) {
			q.setParameter("fromDate", leave.getFromLeaveDate());
			q.setParameter("toDate", leave.getToLeaveDate());
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() == null) {
			q.setParameter("toDate", leave.getToLeaveDate());
		}
		if (leave.getToLeaveDate() == null && leave.getFromLeaveDate() != null) {
			q.setParameter("fromDate", leave.getFromLeaveDate());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(leave.getPager().getPage() * leave.getPager().getPageSize());
		q.setMaxResults(leave.getPager().getPageSize());

		
		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((record) -> {
			Leave custom = new Leave();
			custom.setId((Integer) record[0]);
			custom.setStaffName(record[1].toString());
			custom.setLeaveDate((Date) record[2]);
			custom.setReason((String) record[3]);
			custom.setStatus( (Boolean) record[4]);
			list.add(custom);
		});

		pager.setPageSize(leave.getPager().getPageSize());
		pager.setPage(leave.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}
}
