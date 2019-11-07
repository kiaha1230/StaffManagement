package com.team3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.model.LogAudit;
import com.team3.repository.LogAuditRepository;

@Service
public class LogAuditService {
	@Autowired
	private LogAuditRepository logAuditRepository;
	@PersistenceContext
	private EntityManager em;

	public ArrayList<LogAudit> getAll() {
		List<LogAudit> list = new ArrayList<LogAudit>();
		list = logAuditRepository.findAll();
		return (ArrayList<LogAudit>) list;
	}

	public Optional<LogAudit> getById(int id) {
		return logAuditRepository.findById(id);
	}

	public void add(LogAudit logAudit) {
		logAuditRepository.save(logAudit);
	}

	public void edit(LogAudit logAudit) {
		logAuditRepository.save(logAudit);
	}

	public void deleteLogAudit(int id) {
		logAuditRepository.deleteById(id);
	}

	public ArrayList<LogAudit> getByCondition(LogAudit logAudit) {
		ArrayList<LogAudit> list = new ArrayList<LogAudit>();
		String query = "select a.id, s.staffName,a.travelLogAudit,a.deviceLogAudit, a.mealLogAudit from LogAudit a , Staff s where a.staffId = s.id ";
	

		Query q = em.createQuery(query);
	


		return list;
	}

}
