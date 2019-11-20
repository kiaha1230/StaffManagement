package com.team3.Ultilities;

import org.springframework.beans.factory.annotation.Autowired;

import com.team3.model.LogAudit;
import com.team3.model.LogDetail;
import com.team3.service.LogAuditService;
import com.team3.service.LogDetailService;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;

public class LogFunction {
	@Autowired
	private LogAuditService logAudit;
	@Autowired
	private LogDetailService logDetail;

	public void getDiff(Object oldObj, Object newObj,Integer actionType) {
		DiffNode diff = ObjectDifferBuilder.buildDefault().compare(newObj, oldObj);
		diff.visit(new DiffNode.Visitor() {
			public void node(DiffNode node, Visit visit) {
				final Object baseValue = node.canonicalGet(oldObj);
				final Object workingValue = node.canonicalGet(newObj);


			}
		});
	}

}
