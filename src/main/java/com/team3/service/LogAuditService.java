package com.team3.service;

import java.lang.reflect.Field;
import java.sql.Time;
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
		LogAudit logAudit = new LogAudit();
		List<LogDetail> logDetails = new ArrayList<LogDetail>();
		Field[] elements = oldObj.getClass().getDeclaredFields();
		boolean checkNull = checkDiffOrNot(elements, newObj, oldObj);
		if (checkNull) {
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

	// at least one difference, return true if there's a difference between two obj
	public Boolean checkDiffOrNot(Field[] elements, Object newObj, Object oldObj) {
		boolean returnBol = false;
		for (Field a : elements) {
			a.setAccessible(true);
			try {
				String oldVal = a.get(oldObj).toString();
				String newVal = a.get(newObj).toString();
				if (!oldVal.equals(newVal)) {
					returnBol = true;
					break;
				} else {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnBol;
	}

	// you're the one who suffer.

	// custom getDiff

	public void getDiffCustom(Object oldObj, Object newObj) {
		Integer actionType = 1;
		Field[] elements = oldObj.getClass().getDeclaredFields();
		String tableName = oldObj.getClass().getSimpleName().toUpperCase();
		LogAudit logAudit = new LogAudit();
		if (checkDiffOrNot(elements, newObj, oldObj)) {
			logAudit.setActionDatetime(new Date());
			logAudit.setAccountId(UserInformation.getACCOUNT().getId());
			logAudit.setTableName(tableName);
			logAudit.setActionType(actionType);
			logAuditRepository.save(logAudit);
			for (Field a : elements) {
				a.setAccessible(true);
				try {
					if (!checkName(a)) {
						if (!(a.get(oldObj).equals(a.get(newObj)))) {
							LogDetail logDetail = new LogDetail();
							String colName = Ultilities.getColNameWithoutEC(a.getName());
							if (tableName.equals("ACCOUNT") && colName.equals("STAFF_CODE")) {
								continue;
							}
							logDetail.setOldValue(a.get(oldObj).toString());
							logDetail.setNewValue(a.get(newObj).toString());
							logDetail.setColumnName(colName);
							logDetail.setLogAuditId(logAudit.getId());
							logDetailRepository.save(logDetail);

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else {

		}

	}

	//

	public void addDiff(Object addObj) {
		Integer actionType = 0;
		LogAudit logAudit = new LogAudit();
		logAudit.setActionDatetime(new Date());
		logAudit.setAccountId(UserInformation.getACCOUNT().getId());
		String tableName = addObj.getClass().getSimpleName().toUpperCase();
		logAudit.setTableName(tableName);
		logAudit.setActionType(actionType);
		logAuditRepository.save(logAudit);
		Field[] elements = addObj.getClass().getDeclaredFields();
		for (Field a : elements) {
			if (!checkName(a) && (a.getName() != "fromDate" || a.getName() != "toDate")) {
				Integer logAuditId = logAudit.getId();
				String colName = Ultilities.getColNameWithoutEC(a.getName());
				if ((tableName.equals("ACCOUNT") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("RECORD") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("SALARY") && (colName.equals("TAX") || colName.equals("INSURANCE")
								|| colName.equals("NET_SALARY") || colName.equals("STAFF_CODE")))
						|| (tableName.equals("ALLOWANCE") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("RECRUITMENT") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("LEAVE") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("STAFF") && colName.equals("DEPART_NAME"))
						|| (tableName.equals("STAFF") && colName.equals("POSITION_NAME"))) {
					continue;
				}
				LogDetail logDetail = new LogDetail();
				logDetail.setLogAuditId(logAuditId);
				logDetail.setColumnName(colName);
				if (a.getName().contains("Date") && (a.getName() != "fromDate" || a.getName() != "toDate")) {
					logDetail.setNewValue(Ultilities.dateToStringUSFormat(new Date()));
				} else if (a.getName() == "fromDate" || a.getName() == "toDate") {
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
		String tableName = delelteObj.getClass().getSimpleName().toUpperCase();
		logAudit.setTableName(tableName);
		logAudit.setActionType(actionType);
		logAuditRepository.save(logAudit);
		Field[] elements = delelteObj.getClass().getDeclaredFields();
		for (Field a : elements) {
			if (!checkName(a)) {
				Integer logAuditId = logAudit.getId();
				String colName = Ultilities.getColNameWithoutEC(a.getName());
				if ((tableName.equals("ACCOUNT") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("RECORD") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("SALARY") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("ALLOWANCE") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("RECRUITMENT") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("LEAVE") && colName.equals("STAFF_CODE"))
						|| (tableName.equals("STAFF") && colName.equals("DEPART_NAME"))
						|| (tableName.equals("STAFF") && colName.equals("POSITION_NAME"))) {
					continue;
				}
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

	// check name here

	public Boolean checkName(Field a) {
		if ((a.getName().contains("Name") && !(a.getName().contains("logAuditName"))
				&& !(a.getName().contains("departName")) && !(a.getName().contains("username"))
				&& !(a.getName().contains("positionName"))) || a.getName().contains("pager")
				|| a.getName().contains("from") || a.getName().contains("to") || a.getName().contains("id")
				|| a.getName().contains("kiLuat") || a.getName().contains("khenThuong")
				|| a.getName().contains("password") || a.getName().contains("diem")) {
			return true;
		}
		return false;
	}

	//

	public APIResponse findByCondition(LogAudit logAudit) {
		ArrayList<LogAudit> list = new ArrayList<LogAudit>();
		String query = "select l.id,l.actionDatetime,l.tableName,l.actionType, s.staffName,s.staffCode from LogAudit l , Account a , Staff s where l.accountId = a.id and a.staffId = s.id  ";
		if (!(logAudit.getAccountId() == null)) {
			query += " and  l.accountId = :id ";
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
			query += " and l.actionDatetime >= :fromDate ";
		}
		if (logAudit.getFromDate() == null && logAudit.getToDate() != null) {
			query += " and l.actionDatetime <= :toDate ";
		}
		query += " order by l.actionDatetime desc";

		Query q = em.createQuery(query);
		if (!(logAudit.getAccountId() == null)) {
			q.setParameter("id", logAudit.getAccountId());
		}
		if (!(logAudit.getTableName() == null)) {
			q.setParameter("tableName", logAudit.getTableName());
		}
		if (logAudit.getFromDate() != null && logAudit.getToDate() != null) {
			q.setParameter("fromDate", logAudit.getFromDate());
			Date todate = logAudit.getToDate();
			todate.setHours(23);
			todate.setMinutes(59);
			todate.setSeconds(59);
			q.setParameter("toDate", todate);
		}
		if (logAudit.getFromDate() != null && logAudit.getToDate() == null) {
			q.setParameter("fromDate", logAudit.getFromDate());
		}
		if (logAudit.getFromDate() == null && logAudit.getToDate() != null) {
			Date todate = logAudit.getToDate();
			todate.setHours(23);
			todate.setMinutes(59);
			todate.setSeconds(59);
			q.setParameter("toDate", todate);
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
			custom.setStaffCode((String) logAudits[5]);
			list.add(custom);
		});

		pager.setPageSize(logAudit.getPager().getPageSize());
		pager.setPage(logAudit.getPager().getPage());
		response.setPager(pager);
		response.setData(list);
		return response;
	}

}
