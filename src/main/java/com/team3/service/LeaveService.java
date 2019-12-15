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
import com.team3.model.Allowance;
import com.team3.model.Attendance;
import com.team3.model.Leave;
import com.team3.model.Pager;
import com.team3.model.Record;
import com.team3.repository.LeaveRepository;
import com.team3.repository.RecordRepository;

@Service
public class LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private RecordRepository recordRepository;

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

	// API

	public APIResponse findByCondition(Leave leave) {
		ArrayList<Leave> list = new ArrayList<Leave>();
		String query = "select l.id , s.staffName , l.fromDate,l.toDate , l.reason, l.status,l.accept,s.staffCode,s.id from Leave l , Staff s where l.staffId = s.id and accept != 1 ";
		if (leave.getAccept() != null) {
			query += " and  l.accept = :accept ";
		}
		if (leave.getStaffId() != null) {
			query += " and  l.staffId = :staffId ";
		}
		if (leave.getStatus() != null) {
			query += " and  l.status = :status ";
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() != null) {
			query += " and l.fromDate >= :fromDate  and l.toDate <= :toDate ";
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() == null) {
			query += " and l.toDate <= :toDate ";
		}
		if (leave.getToLeaveDate() == null && leave.getFromLeaveDate() != null) {
			query += " and l.fromDate >= :fromDate ";
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
		if (leave.getAccept() != null) {
			q.setParameter("accept", leave.getAccept());
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
			custom.setFromDate((Date) record[2]);
			custom.setToDate((Date) record[3]);
			custom.setReason((String) record[4]);
			custom.setStatus((Boolean) record[5]);
			custom.setAccept((Integer) record[6]);
			custom.setStaffCode((String) record[7]);
			custom.setStaffId((Integer) record[8]);
			list.add(custom);
		});

		pager.setPageSize(leave.getPager().getPageSize());
		pager.setPage(leave.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public Leave getByIdSQL(Integer id) {
		Leave leave = new Leave();
		String hql = "From Leave where id = :id";
		Query q = em.createQuery(hql);
		q.setParameter("id", id);
		leave = (Leave) q.getSingleResult();
		return leave;
	}

	public List<Leave> getLeavesByStatusAndStaffId(Boolean status, Integer staffId) {
		List<Leave> leaves = new ArrayList<Leave>();
		String hql = "From Leave where staffId = :staffId and status = :status";
		Query q = em.createQuery(hql);
		q.setParameter("staffId", staffId);
		q.setParameter("status", status);
		leaves = q.getResultList();
		return leaves;

	}

	public Boolean isAnAnnualLeaveInMonth(Integer staffId, Integer month, Integer year) {
		List<Leave> leave = new ArrayList<Leave>();
		String hql = "From Leave where staffId = :staffId and MONTH(fromDate) = :month and YEAR(fromDate) = :year and status=1 and accept=2";
		Query q = em.createQuery(hql);
		q.setParameter("staffId", staffId);
		q.setParameter("month", month);
		q.setParameter("year", year);
		leave = q.getResultList();
		if (leave.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	public APIResponse findByConditionPending(Leave leave) {
		ArrayList<Leave> list = new ArrayList<Leave>();
		String query = "select l.id , s.staffName , l.fromDate,l.toDate , l.reason, l.status,l.accept,s.id,s.staffCode from Leave l , Staff s where l.staffId = s.id and l.accept = 1 ";
		if (leave.getStaffId() != null) {
			query += " and  l.staffId = :staffId ";
		}
		if (leave.getStatus() != null) {
			query += " and  l.status = :status ";
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() != null) {
			query += " and l.fromDate >= :fromDate  and l.toDate <= :toDate ";
		}
		if (leave.getToLeaveDate() != null && leave.getFromLeaveDate() == null) {
			query += " and l.toDate <= :toDate ";
		}
		if (leave.getToLeaveDate() == null && leave.getFromLeaveDate() != null) {
			query += " and l.fromDate >= :fromDate ";
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
			custom.setFromDate((Date) record[2]);
			custom.setToDate((Date) record[3]);
			custom.setReason((String) record[4]);
			custom.setStatus((Boolean) record[5]);
			custom.setAccept((Integer) record[6]);
			custom.setStaffId((Integer) record[7]);
			custom.setStaffCode((String) record[8]);
			list.add(custom);
		});

		pager.setPageSize(leave.getPager().getPageSize());
		pager.setPage(leave.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

	public APIResponse getByStaffId(Leave leave) {
		ArrayList<Leave> list = new ArrayList<Leave>();
		String query = "select l.id , s.staffName , l.fromDate,l.toDate , l.reason, l.status,l.accept,s.id,s.staffCode from Leave l , Staff s where l.staffId = s.id and l.staffId = :staffId";
		Query q = em.createQuery(query);
		q.setParameter("staffId", leave.getStaffId());
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
			custom.setFromDate((Date) record[2]);
			custom.setToDate((Date) record[3]);
			custom.setReason((String) record[4]);
			custom.setStatus((Boolean) record[5]);
			custom.setAccept((Integer) record[6]);
			custom.setStaffId((Integer) record[7]);
			custom.setStaffCode((String) record[8]);
			list.add(custom);
		});

		pager.setPageSize(leave.getPager().getPageSize());
		pager.setPage(leave.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

}
