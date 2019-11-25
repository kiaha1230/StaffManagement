package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.Depart;
import com.team3.model.LogDetail;
import com.team3.repository.LogDetailRepository;
import com.team3.model.LogDetail;

@Service
public class LogDetailService {
	@Autowired
	private LogDetailRepository logDetailRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<LogDetail> getAllLogDetail() {
		List<LogDetail> list = new ArrayList<LogDetail>();
		list = logDetailRepository.findAll();
		return (ArrayList<LogDetail>) list;
	}

	public Optional<LogDetail> getById(int id) {
		return logDetailRepository.findById(id);
	}

	public void addLogDetail(LogDetail logDetail) {
		logDetailRepository.save(logDetail);
	}

	public void editLogDetail(LogDetail logDetail) {
		logDetailRepository.save(logDetail);
	}

	public void deleteLogDetail(int id) {
		logDetailRepository.deleteById(id);
	}

	public ArrayList<LogDetail> getByCondition(LogDetail logDetail) {
		ArrayList<LogDetail> list = new ArrayList<LogDetail>();
//		String query = "select a.id, a.username,a.password,a.createDate, s.staffName,a.logDetailRole from LogDetail a , Staff s where a.staffId = s.id ";
//		Date fromDate = new Date();
//		Date toDate = new Date();
//		Date createDate = new Date();
//		if (!(logDetail.getUsername() == null)) {
//			query += " and  a.username like :username";
//		}
//		// ngay
//		if (logDetail.getFromDate() != null && logDetail.getToDate() != null) {
//			query += " and a.createDate between :fromDate and :toDate ";
//		}
//		if (logDetail.getFromDate() != null && logDetail.getToDate() == null) {
//			query += " and a.createDate >= :fromDate ";
//		}
//		if (logDetail.getFromDate() == null && logDetail.getToDate() != null) {
//			query += " and a.createDate <= :toDate ";
//		}
//
//		//
//		if (!(logDetail.getStaffId() == null)) {
//			query += " and s.id = :staffId  ";
//		}
//		if (logDetail.isLogDetailRole() != null) {
//			query += " and a.logDetailRole = :logDetailRole ";
//		}
//		Query q = em.createQuery(query);
//	q.setParameter("createDate", logDetail.getCreateDate());
//			q.setParameter("toDate", logDetail.getToDate());
//		}
//
//		List<Object[]> obj = q.getResultList();
//		obj.stream().forEach((record) -> {
//			LogDetail custom = new LogDetail();
//			custom.setId((Integer) record[0]);
//			custom.setUsername(record[1].toString());
//			custom.setPassword(record[2].toString());
//			custom.setCreateDate((Date) record[3]);
//			custom.setStaffName(record[4].toString());
//			custom.setLogDetailRole((Boolean) record[5]);
//			list.add(custom);
//		});
		return list;
	}

	public List<LogDetail> getByLogAuditId(Integer logAuditId) {
		List<LogDetail> list = new ArrayList<LogDetail>();
		String hql = "From LogDetail where logAuditId = :id";
		Query q = em.createQuery(hql);
		q.setParameter("id", logAuditId);
		list = q.getResultList();
		return list;
	}

}
