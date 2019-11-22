package com.team3.Ultilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.customModel.OldNewValue;
import com.team3.model.LogAudit;
import com.team3.model.LogDetail;
import com.team3.repository.LogAuditRepository;
import com.team3.resources.UserInformation;
import com.team3.service.LogAuditService;
import com.team3.service.LogDetailService;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;

public class LogFunction {
	@Autowired
	private LogAuditRepository logAuditRepository;
	@Autowired
	private LogDetailService logDetailService;

//	public void getDiff(Object oldObj, Object newObj, Integer actionType) {
//		try {
//			LogAuditService logAuditService = new LogAuditService();
//			LogAudit logAudit = new LogAudit();
//			logAudit.setActionDatetime(new Date());
//			logAudit.setAccountId(UserInformation.getACCOUNT().getId());
//			logAudit.setTableName(oldObj.getClass().getSimpleName().toUpperCase());
//			logAudit.setActionType(actionType);
//			logAuditRepository.save(logAudit);
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
////		try {
////			DiffNode diff = ObjectDifferBuilder.buildDefault().compare(newObj, oldObj);
////			diff.visit(new DiffNode.Visitor() {
////				public void node(DiffNode node, Visit visit) {
////					final Object baseValue = node.canonicalGet(oldObj);
////					final Object workingValue = node.canonicalGet(newObj);
////					LogDetail logDetail = new LogDetail();
////					logDetail.setOldValue(baseValue.toString());
////					logDetail.setNewValue(workingValue.toString());
////					logDetail.setColumnName(Ultilities.getColName(node.getPath().toString()));
////					logDetail.setLogAuditId(logAuditService.getByCondition(logAudit).get(0).getId());
////					logDetailService.addLogDetail(logDetail);
////				}
////			});
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//
//	}

}
