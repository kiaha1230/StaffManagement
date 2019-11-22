package com.team3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.team3.Ultilities.Ultilities;
import com.team3.model.LogAudit;
import com.team3.model.LogDetail;
import com.team3.repository.LogAuditRepository;
import com.team3.repository.LogDetailRepository;
import com.team3.resources.UserInformation;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;

@Service
public class LogAuditService {
	@Autowired
	private LogAuditRepository logAuditRepository;
	@Autowired
	private LogDetailRepository logDetailRepository;
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

	public void getDiff(Object oldObj, Object newObj, Integer actionType) {
		List<LogDetail> logDetails = new ArrayList<LogDetail>();
		LogAudit logAudit = new LogAudit();
		logAudit.setActionDatetime(new Date());
		logAudit.setAccountId(UserInformation.getACCOUNT().getId());
		logAudit.setTableName(oldObj.getClass().getSimpleName().toUpperCase());
		logAudit.setActionType(actionType);
		logAuditRepository.save(logAudit);

		DiffNode diff = ObjectDifferBuilder.buildDefault().compare(newObj, oldObj);
		diff.visit(new DiffNode.Visitor() {
			public void node(DiffNode node, Visit visit) {
				final Object baseValue = node.canonicalGet(oldObj);
				final Object workingValue = node.canonicalGet(newObj);
				LogDetail logDetail = new LogDetail();
				logDetail.setOldValue(baseValue.toString());
				logDetail.setNewValue(workingValue.toString());
				logDetail.setColumnName(Ultilities.getColName(node.getPath().toString()));
				logDetail.setLogAuditId(logAudit.getId());
				logDetails.add(logDetail);
			}
		});
		for (int i = 1; i <= logDetails.size() - 1; i++) {
			logDetailRepository.save(logDetails.get(i));
		}

	}

}
