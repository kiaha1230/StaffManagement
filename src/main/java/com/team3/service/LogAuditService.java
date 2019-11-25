package com.team3.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.team3.Ultilities.Ultilities;
import com.team3.model.APIResponse;
import com.team3.model.LogAudit;
import com.team3.model.LogDetail;
import com.team3.model.Pager;
import com.team3.model.LogAudit;
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


	public void getDiff(Object oldObj, Object newObj) {
		Integer actionType = 1;
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

	public void addDiff(Object addObj) {
		Integer actionType = 0;
		LogAudit logAudit = new LogAudit();
		logAudit.setActionDatetime(new Date());
		logAudit.setAccountId(UserInformation.getACCOUNT().getId());
		logAudit.setTableName(addObj.getClass().getSimpleName().toUpperCase());
		logAudit.setActionType(actionType);
		logAuditRepository.save(logAudit);
		Field[] elements = addObj.getClass().getDeclaredFields();
		for (Field a : elements) {

			if (!checkName(a)) {
				Integer logAuditId = logAudit.getId();
				String colName = Ultilities.getColNameWithoutEC(a.getName());
				LogDetail logDetail = new LogDetail();
				logDetail.setLogAuditId(logAuditId);
				logDetail.setColumnName(colName);
				if (a.getName().contains("Date") && a.getName() != "leaveDate") {
					logDetail.setNewValue(Ultilities.dateToStringUSFormat(new Date()));
				} else if (a.getName() == "leaveDate") {
					try {
						a.setAccessible(true);
						Date fieldVal = (Date) a.get(addObj);
						logDetail.setNewValue(Ultilities.dateToStringUSFormat(fieldVal));
					} catch (Exception e) {

						e.printStackTrace();
					}
				} else {
					try {
						a.setAccessible(true);
						Object fieldVal = a.get(addObj);
						logDetail.setNewValue(fieldVal.toString());
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
				logDetailRepository.save(logDetail);

			}
		}
	}

	public void deleteDiff(Object delelteObj) {
		Integer actionType = 2;
		LogAudit logAudit = new LogAudit();
		logAudit.setActionDatetime(new Date());
		logAudit.setAccountId(UserInformation.getACCOUNT().getId());
		logAudit.setTableName(delelteObj.getClass().getSimpleName().toUpperCase());
		logAudit.setActionType(actionType);
		logAuditRepository.save(logAudit);
		Field[] elements = delelteObj.getClass().getDeclaredFields();
		for (Field a : elements) {
			if (!checkName(a)) {
				Integer logAuditId = logAudit.getId();
				String colName = Ultilities.getColNameWithoutEC(a.getName());
				LogDetail logDetail = new LogDetail();
				logDetail.setLogAuditId(logAuditId);
				logDetail.setColumnName(colName);
				if (a.getName().contains("Date")) {
					logDetail.setOldValue(Ultilities.dateToStringUSFormat(new Date()));
				} else {
					try {
						a.setAccessible(true);
						Object fieldVal = a.get(delelteObj);
						logDetail.setOldValue(fieldVal.toString());
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
				logDetailRepository.save(logDetail);

			}
		}
	}

	public Boolean checkName(Field a) {
		if ((a.getName().contains("Name") && !(a.getName().contains("logAuditName"))
				&& !(a.getName().contains("departName")) && !(a.getName().contains("username"))
				&& !(a.getName().contains("positionName"))) || a.getName().contains("pager")
				|| a.getName().contains("from") || a.getName().contains("to") || a.getName().contains("id")) {
			return true;
		}
		return false;
	}

	public APIResponse findByCondition(LogAudit logAudit) {
		ArrayList<LogAudit> list = new ArrayList<LogAudit>();
		String query = "select l.id,l.actionDatetime,l.tableName,l.actionType, s.staffName from LogAudit l , Account a , Staff s where l.accountId = a.id and a.staffId = s.id  ";
		if (!(logAudit.getStaffName() == null)) {
			query += " and  s.staffName like :staffName ";
		}
		if (!(logAudit.getTableName() == null)) {
			query += " and l.tableName like :tableName ";
		}
		if (!(logAudit.getActionType() == null)) {
			query += " and l.actionType = :actionType ";
		}

		if (logAudit.getFromDate() != null && logAudit.getToDate() != null) {
			query += " and l.actionDatetime between :fromDate and :toDate ";
		}
		if (logAudit.getFromDate() != null && logAudit.getToDate() == null) {
			query += " and r.createDate >= :fromDate ";
		}
		if (logAudit.getFromDate() == null && logAudit.getToDate() != null) {
			query += " and r.createDate <= :toDate ";
		}
		query += " order by l.actionDatetime desc";

		Query q = em.createQuery(query);
		if (!(logAudit.getStaffName() == null)) {
			q.setParameter("staffName", "%" + logAudit.getStaffName() + "%");
		}
		if (!(logAudit.getTableName() == null)) {
			q.setParameter("tableName", logAudit.getTableName());
		}
		if (logAudit.getFromDate() != null && logAudit.getToDate() != null) {
			q.setParameter("fromDate", logAudit.getFromDate());
			q.setParameter("toDate", logAudit.getToDate());
		}
		if (logAudit.getFromDate() != null && logAudit.getToDate() == null) {
			q.setParameter("fromDate", logAudit.getFromDate());
		}
		if (logAudit.getFromDate() == null && logAudit.getToDate() != null) {
			q.setParameter("toDate", logAudit.getToDate());
		}
		if (!(logAudit.getActionType() == null)) {
			q.setParameter("actionType", logAudit.getActionType());
		}

		APIResponse response = new APIResponse();
		Pager pager = new Pager();
		List<Object[]> totalRow = q.getResultList();
		pager.setTotalRow(totalRow.size());
		q.setFirstResult(logAudit.getPager().getPage() * logAudit.getPager().getPageSize());
		q.setMaxResults(logAudit.getPager().getPageSize());

		List<Object[]> obj = q.getResultList();
		obj.stream().forEach((logAudits) -> {
			LogAudit custom = new LogAudit();
			custom.setId((Integer) logAudits[0]);
			custom.setActionDatetime((Date) logAudits[1]);
			custom.setTableName((String) logAudits[2]);
			custom.setActionType((Integer) logAudits[3]);
			custom.setStaffName((String) logAudits[4]);
			list.add(custom);
		});

		pager.setPageSize(logAudit.getPager().getPageSize());
		pager.setPage(logAudit.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

}
